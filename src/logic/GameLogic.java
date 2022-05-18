package logic;

import base.Attackable;
import base.BaseUnit;
import base.Buffable;
import base.Debuffable;
import constant.BoardConstant;
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
import util.Timer;
import javafx.application.Platform;

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
    private static boolean gameActive = false;
    private static boolean timerActive = false;
    private static Thread thread = null;

    public static void init() {
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                boardState[i][j] = SquareOwnerState.EMPTY;
                boardUnits[i][j] = null;
            }
        }
        initPlayer(SquareOwnerState.PLAYER1);
        initPlayer(SquareOwnerState.PLAYER2);
    }

    private static void initPlayer(SquareOwnerState state) {
        int row = state == SquareOwnerState.PLAYER1 ? 0 : BoardConstant.ROW_NUMBER - 1;
        setBoardSquare(new NormalUnit(state), state, row, 0);
        setBoardSquare(new FlyingUnit(state), state, row, 1);
        setBoardSquare(new ShooterUnit(state), state, row, 2);
        setBoardSquare(new HealerUnit(state), state, row, 3);
        setBoardSquare(new VenomUnit(state), state, row, 4);
        setBoardSquare(new FreezerUnit(state), state, row, 5);
        setBoardSquare(new NormalUnit(state), state, row, 6);
        setBoardSquare(new NormalUnit(state), state, row, 7);
    }

    private static void setBoardSquare(BaseUnit unit, SquareOwnerState state, int xPosition, int yPosition) {
        boardState[xPosition][yPosition] = state;
        boardUnits[xPosition][yPosition] = unit;
    }

    public static void movePreview(int xPosition, int yPosition) {
        boardPane.resetAllPreviewState();
        System.out.println("MOVEPRVIEW");
        if ((selectedXPosition == xPosition && selectedYPosition == yPosition
                && currentPlayerState == CurrentPlayerState.PREVIEW_MOVE)
                || (boardState[xPosition][yPosition] != currentPlayer)) {
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
        System.out.println("ATKPRVIEW");

        if ((selectedXPosition == xPosition && selectedYPosition == yPosition
                && currentPlayerState == CurrentPlayerState.PREVIEW_ATTACK)
                || (boardState[xPosition][yPosition] != currentPlayer)) {
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
        System.out.println("MOVE" + xPosition + yPosition);
        boardState[xPosition][yPosition] = boardState[selectedXPosition][selectedYPosition];
        boardState[selectedXPosition][selectedYPosition] = SquareOwnerState.EMPTY;
        boardUnits[xPosition][yPosition] = boardUnits[selectedXPosition][selectedYPosition];
        boardUnits[selectedXPosition][selectedYPosition] = null;
        boardPane.move(xPosition, yPosition);
        toggleCurrentPlayer();
        AudioUtil.playSound("move.wav");
    }

    public static void attack(int xPosition, int yPosition) {
        BaseUnit selectedUnit = boardUnits[selectedXPosition][selectedYPosition];
        BaseUnit targetUnit = boardUnits[xPosition][yPosition];
        System.out.println("ATK" + selectedUnit);

        if (selectedUnit instanceof Attackable) {
            // if (boardState[xPosition][yPosition] == currentPlayer)
            // return;
            Attackable attacker = (Attackable) selectedUnit;
            attacker.attackUnit(targetUnit);
            if (selectedUnit instanceof NormalUnit) {
                AudioUtil.playSound("attack-melee.wav");
            } else {
                AudioUtil.playSound("attack-ranged.wav", 0.5);
            }
        }
        if (selectedUnit instanceof Debuffable) {
            // if (boardState[xPosition][yPosition] == currentPlayer)
            // return;
            Debuffable attacker = (Debuffable) selectedUnit;
            attacker.debuffUnit(targetUnit);
            if (selectedUnit instanceof VenomUnit) {
                AudioUtil.playSound("poison.wav");
            } else if (selectedUnit instanceof FreezerUnit) {
                AudioUtil.playSound("freeze.wav", 0.5);
            }
        }
        if (selectedUnit instanceof Buffable) {
            // if (boardState[xPosition][yPosition] != currentPlayer)
            // return;
            Buffable healer = (Buffable) selectedUnit;
            healer.buffUnit(targetUnit);
            if (selectedUnit instanceof HealerUnit) {
                AudioUtil.playSound("heal.wav", 0.5);
            }
        }
        boardPane.updateUnit(xPosition, yPosition);
        toggleCurrentPlayer();
    }

    // public static void resetSelectedAndRerender() {
    // // Remove dead unit
    // for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
    // for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
    // if (boardUnits[i][j] != null && boardUnits[i][j].getCurrentHealth() <= 0) {
    // statusPane.reduceUnit(boardState[i][j]);
    // boardState[i][j] = SquareOwnerState.EMPTY;
    // boardUnits[i][j] = null;
    // boardPane.removeItem(i, j);
    // }
    // }
    // }
    // selectedXPosition = -1;
    // selectedYPosition = -1;
    // setCurrentPlayerState(SquarePreviewState.NONE);

    // }
    private static void removeDeadUnits() {
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                if (boardUnits[i][j] != null && boardUnits[i][j].getCurrentHealth() <= 0) {
                    statusPane.reduceUnit(boardState[i][j]);
                    boardState[i][j] = SquareOwnerState.EMPTY;
                    boardUnits[i][j] = null;
                    boardPane.updateUnit(i, j);
                }
            }
    }

    public static void toggleCurrentPlayer() {
        setSelectedXPosition(-1);
        setSelectedYPosition(-1);
        boardPane.resetAllPreviewState();
        removeDeadUnits();
        if (currentPlayer == SquareOwnerState.PLAYER1)
            GameLogic.currentPlayer = SquareOwnerState.PLAYER2;
        else
            GameLogic.currentPlayer = SquareOwnerState.PLAYER1;
        GameLogic.setTimerActive(false);
        Timer.setTimer(TimeConstant.TIME_PER_TURN);
        GameLogic.setTimerActive(true);
        getStatusPane().toggleTurn();
    }

    // TIMER
    public static void startTimer() {
        thread = new Thread(() -> {
            try {
                while (!Timer.isTimeOver() && isTimerActive() && isGameActive()) {
                    Thread.sleep(20);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Timer.setTimer(Timer.getTimer() - 20);
                            actionPane.getTimerPane().setTime();
                        }
                    });
                }
                if (Timer.isTimeOver() && isGameActive()) {
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            // gameOver();
                            toggleCurrentPlayer();
                        }
                    });
                }
            } catch (

            InterruptedException e) {
                // e.printStackTrace();
                System.out.println("Timer Thread Interrupted");
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
        System.out.println("Game Over");
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

}
