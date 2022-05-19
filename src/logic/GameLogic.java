package logic;

import java.util.Random;

import base.Attackable;
import base.BasePotion;
import base.BaseUnit;
import base.Buffable;
import base.Debuffable;
import constant.BoardConstant;
import constant.PotionConstant;
import constant.TimeConstant;
import gui.ActionPane;
import gui.BoardPane;
import gui.StatusPane;
import unit.FlyingUnit;
import unit.FreezerUnit;
import unit.HealerUnit;
import unit.NormalUnit;
import unit.ShooterUnit;
import unit.VenomUnit;
import util.AudioUtil;
import util.TimerUtil;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import potion.BuffPotion;
import potion.HealingPotion;
import potion.ToxicPotion;

public class GameLogic {
    private static SquareOwnerState currentPlayer = SquareOwnerState.PLAYER1;
    private static int selectedXPosition = -1;
    private static int selectedYPosition = -1;
    private static CurrentPlayerState currentPlayerState = CurrentPlayerState.PENDING;
    private static BoardPane boardPane;
    private static ActionPane actionPane;
    private static StatusPane statusPane;
    private static SquareOwnerState[][] boardState = new SquareOwnerState[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static BaseUnit[][] boardUnits = new BaseUnit[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static BasePotion[][] boardPotions = new BasePotion[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static boolean gameActive = false;
    private static boolean timerActive = false;
    private static Thread thread = null;
    private static int roundCounter = 1;
    private static int potionCounter = 0;
    private static SquareOwnerState winner = null;

    public static void init() {
        winner = null;
        roundCounter = 1;
        potionCounter = 0;
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                boardState[i][j] = SquareOwnerState.EMPTY;
                boardUnits[i][j] = null;
                boardPotions[i][j] = null;
            }
        }
        initPlayer(SquareOwnerState.PLAYER1);
        initPlayer(SquareOwnerState.PLAYER2);
    }

    private static void initPlayer(SquareOwnerState state) {
        int row = state == SquareOwnerState.PLAYER1 ? 0 : BoardConstant.ROW_NUMBER - 1;
        int row2 = Math.abs(row - 1);
        setBoardSquare(new FlyingUnit(state), state, row, 2);
        setBoardSquare(new VenomUnit(state), state, row, 3);
        setBoardSquare(new FreezerUnit(state), state, row, 4);
        setBoardSquare(new HealerUnit(state), state, row, 5);
        setBoardSquare(new ShooterUnit(state), state, row, 6);
        setBoardSquare(new NormalUnit(state), state, row2, 1);
        setBoardSquare(new NormalUnit(state), state, row2, 3);
        setBoardSquare(new NormalUnit(state), state, row2, 5);
        setBoardSquare(new NormalUnit(state), state, row2, 7);
    }

    private static void setBoardSquare(BaseUnit unit, SquareOwnerState state, int xPosition, int yPosition) {
        boardState[xPosition][yPosition] = state;
        boardUnits[xPosition][yPosition] = unit;
    }

    public static void movePreview(int xPosition, int yPosition) {
        boardPane.resetAllPreviewState();
        System.out.println("movePreview");
        if ((selectedXPosition == xPosition && selectedYPosition == yPosition
                && currentPlayerState == CurrentPlayerState.PREVIEW_MOVE)
                || (boardState[xPosition][yPosition] != currentPlayer)
                || boardUnits[xPosition][yPosition].getStunRoundLeft() > 0) {
            setCurrentPlayerState(CurrentPlayerState.PENDING);
            return;
        } else {
            boardPane.movePreview(xPosition, yPosition);
            setSelectedXPosition(xPosition);
            setSelectedYPosition(yPosition);
            setCurrentPlayerState(CurrentPlayerState.PREVIEW_MOVE);
        }
    }

    public static void attackPreview(int xPosition, int yPosition) {
        boardPane.resetAllPreviewState();
        System.out.println("attackPreview");

        if ((selectedXPosition == xPosition && selectedYPosition == yPosition
                && currentPlayerState == CurrentPlayerState.PREVIEW_ATTACK)
                || (boardState[xPosition][yPosition] != currentPlayer)
                || boardUnits[xPosition][yPosition].getStunRoundLeft() > 0) {
            setCurrentPlayerState(CurrentPlayerState.PENDING);
            return;
        } else {
            boardPane.attackPreview(xPosition, yPosition);
            setSelectedXPosition(xPosition);
            setSelectedYPosition(yPosition);
            setCurrentPlayerState(CurrentPlayerState.PREVIEW_ATTACK);
        }
    }

    public static void move(int xPosition, int yPosition) {
        System.out.println("MOVE: " + xPosition + " " + yPosition);
        if (boardState[xPosition][yPosition] == SquareOwnerState.POTION) {
            boardPotions[xPosition][yPosition].consume(getSelectedUnit());
            boardPotions[xPosition][yPosition] = null;
        }
        boardState[xPosition][yPosition] = boardState[selectedXPosition][selectedYPosition];
        boardState[selectedXPosition][selectedYPosition] = SquareOwnerState.EMPTY;
        boardUnits[xPosition][yPosition] = getSelectedUnit();
        boardUnits[selectedXPosition][selectedYPosition] = null;
        // boardPane.move(xPosition, yPosition);
        boardPane.getAllSquares()[GameLogic.getSelectedXPosition()][GameLogic.getSelectedYPosition()].setUnit(null);
        GameLogic.getActionPane().getStatsPane().showUnitStats(GameLogic.getBoardUnits()[xPosition][yPosition],
                xPosition, yPosition);
        toggleCurrentPlayer();
        AudioUtil.playSound("move.wav");
    }

    public static void attack(int xPosition, int yPosition) {
        BaseUnit selectedUnit = getSelectedUnit();
        BaseUnit targetUnit = boardUnits[xPosition][yPosition];
        System.out.println("ATTACK: " + selectedUnit);

        if (selectedUnit instanceof Attackable) {
            Attackable attacker = (Attackable) selectedUnit;
            attacker.attackUnit(targetUnit);
            if (targetUnit.getCurrentHealth() <= 0) {
                selectedUnit.upgrade();
                AudioUtil.playSound("upgrade.wav");
            }
        }
        if (selectedUnit instanceof Debuffable) {
            Debuffable attacker = (Debuffable) selectedUnit;
            attacker.debuffUnit(targetUnit);
        }
        if (selectedUnit instanceof Buffable) {
            if (boardState[xPosition][yPosition] != currentPlayer)
                return;
            Buffable healer = (Buffable) selectedUnit;
            healer.buffUnit(targetUnit);
        }
        GameLogic.getActionPane().getStatsPane().showUnitStats(GameLogic.getBoardUnits()[xPosition][yPosition],
                xPosition, yPosition);
        toggleCurrentPlayer();
    }

    private static void updateAllUnits() {
        int potions = 0;
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                if (boardUnits[i][j] != null) {
                    if (boardUnits[i][j].getStunRoundLeft() > 0) {
                        boardUnits[i][j].setStunRoundLeft(boardUnits[i][j].getStunRoundLeft() - 1);
                    }
                    if (boardUnits[i][j].getVenomRoundLeft() > 0) {
                        boardUnits[i][j].setVenomRoundLeft(boardUnits[i][j].getVenomRoundLeft() - 1);
                        boardUnits[i][j].reduceHealth(VenomUnit.getPoisonpower());
                    }
                    if (boardUnits[i][j].getCurrentHealth() <= 0) {
                        statusPane.reduceUnit(boardState[i][j]);
                        boardState[i][j] = SquareOwnerState.EMPTY;
                        boardUnits[i][j] = null;
                    }
                    boardPane.getAllSquares()[i][j].updateUnit();
                }
                if (boardPotions[i][j] != null) {
                    boardPotions[i][j].setAge(boardPotions[i][j].getAge() - 1);
                    boardPane.getAllSquares()[i][j].addPotion(boardPotions[i][j]);
                    if (boardPotions[i][j].getAge() <= 0) {
                        boardState[i][j] = SquareOwnerState.EMPTY;
                        boardPotions[i][j] = null;
                        boardPane.getAllSquares()[i][j].updateUnit();
                    } else
                        potions++;
                }
            }
        setPotionCounter(potions);
    }

    private static void generatePotion() {
        int xPosition = (BoardConstant.ROW_NUMBER / 2) - (new Random().nextInt(3) - 1);
        int yPosition = new Random().nextInt(BoardConstant.COLOUMN_NUMBER);
        int randomIndex = new Random().nextInt(PotionConstant.POTION_TYPES);
        BasePotion randomPotion = randomIndex == 0 ? new HealingPotion()
                : randomIndex == 1 ? new ToxicPotion() : new BuffPotion();
        System.out.println(xPosition + " " + yPosition + " " + (BoardConstant.ROW_NUMBER / 2) + xPosition);
        if (boardState[xPosition][yPosition] == SquareOwnerState.EMPTY) {
            boardPane.getAllSquares()[xPosition][yPosition].addPotion(randomPotion);
            boardPotions[xPosition][yPosition] = randomPotion;
            boardState[xPosition][yPosition] = SquareOwnerState.POTION;
        }
    }

    public static void toggleCurrentPlayer() {
        setSelectedXPosition(-1);
        setSelectedYPosition(-1);
        boardPane.resetAllPreviewState();
        updateAllUnits();
        if (roundCounter % PotionConstant.ROUND_PER_POTION == 0
                && potionCounter < PotionConstant.MAX_POTION_ON_BOARD) {
            generatePotion();
        }
        if (winner != null) {
            gameOver();
        }
        if (currentPlayer == SquareOwnerState.PLAYER1)
            GameLogic.currentPlayer = SquareOwnerState.PLAYER2;
        else
            GameLogic.currentPlayer = SquareOwnerState.PLAYER1;
        GameLogic.setTimerActive(false);
        TimerUtil.setTimer(TimeConstant.TIME_PER_TURN);
        GameLogic.setTimerActive(true);
        getStatusPane().toggleTurn();
        roundCounter++;
    }

    // TIMER
    public static void startTimer() {
        thread = new Thread(() -> {
            try {
                while (!TimerUtil.isTimeOver() && isTimerActive() && isGameActive()) {
                    Thread.sleep(20);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            TimerUtil.setTimer(TimerUtil.getTimer() - 20);
                            actionPane.getTimerPane().setTime();
                        }
                    });
                }
                if (TimerUtil.isTimeOver() && isGameActive()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            toggleCurrentPlayer();
                        }
                    });
                }
            } catch (InterruptedException e) {
                // System.out.println("Timer Thread Interrupted");
            }
        });
        thread.start();
    }

    public static void stopTimer() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public static void gameOver() {
        if (!isGameActive()) {
            return;
        }
        setGameActive(false);
        currentPlayer = SquareOwnerState.PLAYER1;
        System.out.println("Game Over");
        if (winner == null) {
            return;
        }
        statusPane.getSkipTurnBtn().setDisable(true);
        statusPane.getToggleTimerBtn().setDisable(true);
        String winnerAnnounce = "The winner is " + (winner == SquareOwnerState.PLAYER1 ? BoardConstant.PLAYER1_NAME
                : BoardConstant.PLAYER2_NAME) + "!!!";
        if (winner == SquareOwnerState.EMPTY) {
            winnerAnnounce = "The result is DRAW";
        }
        Alert alert = new Alert(AlertType.INFORMATION, winnerAnnounce);
        alert.setTitle("End");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // GETTER & SETTER
    public static SquareOwnerState[][] getBoardState() {
        return boardState;
    }

    public static BaseUnit[][] getBoardUnits() {
        return boardUnits;
    }

    public static BoardPane getBoardPane() {
        return boardPane;
    }

    public static SquareOwnerState getCurrentPlayer() {
        return currentPlayer;
    }

    public static int getSelectedXPosition() {
        return selectedXPosition;
    }

    public static int getSelectedYPosition() {
        return selectedYPosition;
    }

    public static void setBoardPane(BoardPane boardPane) {
        GameLogic.boardPane = boardPane;
    }

    public static CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public static void setCurrentPlayerState(CurrentPlayerState currentPlayerState) {
        GameLogic.currentPlayerState = currentPlayerState;
    }

    public static boolean isGameActive() {
        return gameActive;
    }

    public static boolean isTimerActive() {
        return timerActive;
    }

    public static void setGameActive(boolean b) {
        gameActive = b;
    }

    public static void setTimerActive(boolean b) {
        timerActive = b;
        if (isTimerActive()) {
            statusPane.getToggleTimerBtn().setText("Pause Timer");
            startTimer();
        } else {
            statusPane.getToggleTimerBtn().setText("Resume Timer");
            stopTimer();
        }
    }

    public static ActionPane getActionPane() {
        return actionPane;
    }

    public static void setActionPane(ActionPane aPane) {
        actionPane = aPane;
    }

    public static StatusPane getStatusPane() {
        return statusPane;
    }

    public static void setStatusPane(StatusPane sPane) {
        statusPane = sPane;
    }

    public static void setSelectedXPosition(int selectedXPosition) {
        GameLogic.selectedXPosition = selectedXPosition;
    }

    public static void setSelectedYPosition(int selectedYPosition) {
        GameLogic.selectedYPosition = selectedYPosition;
    }

    public static BaseUnit getSelectedUnit() {
        if (selectedXPosition == -1 || selectedYPosition == -1)
            return null;
        return boardUnits[selectedXPosition][selectedYPosition];
    }

    public static void setWinner(SquareOwnerState state) {
        winner = state;
    }

    public static SquareOwnerState getWinner() {
        return winner;
    }

    public static int getPotionCounter() {
        return potionCounter;
    }

    public static void setPotionCounter(int potionCounter) {
        GameLogic.potionCounter = potionCounter;
    }

    public static BasePotion[][] getBoardPotions() {
        return boardPotions;
    }

    public static void setBoardPotions(BasePotion[][] boardPotions) {
        GameLogic.boardPotions = boardPotions;
    }

    public static int getRoundCounter() {
        return roundCounter;
    }

    public static void setRoundCounter(int roundCounter) {
        GameLogic.roundCounter = roundCounter;
    }
}
