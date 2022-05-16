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
import unit.NormalUnit;
import unit.ShooterUnit;
import util.Timer;
import javafx.application.Platform;

public class GameLogic {
    private static BoardSquareState currentPlayer = BoardSquareState.PLAYER1;
    private static int selectedXPosition = -1;
    private static int selectedYPosition = -1;
    private static PlayerState currentPlayerState = PlayerState.NONE;
    private static BoardPane boardPane;
    private static ActionPane actionPane;
    private static StatusPane statusPane;
    private static BoardSquareState[][] boardState = new BoardSquareState[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static BaseUnit[][] boardUnits = new BaseUnit[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static boolean gameActive = false;
    private static boolean timerActive = false;
    private static Thread thread = null;

    public static void init() {
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                boardState[i][j] = BoardSquareState.EMPTY;
                boardUnits[i][j] = null;
            }
        }
        initPlayer(BoardSquareState.PLAYER1);
        initPlayer(BoardSquareState.PLAYER2);
    }

    private static void initPlayer(BoardSquareState state) {
        int row = state == BoardSquareState.PLAYER1 ? 0 : BoardConstant.ROW_NUMBER - 1;
        setBoardSquare(new NormalUnit(), state, row, 0);
        setBoardSquare(new ShooterUnit(), state, row, 1);
        setBoardSquare(new FlyingUnit(), state, row, 2);
        setBoardSquare(new NormalUnit(), state, row, 3);
        setBoardSquare(new NormalUnit(), state, row, 4);
        setBoardSquare(new NormalUnit(), state, row, 5);
        setBoardSquare(new NormalUnit(), state, row, 6);
        setBoardSquare(new NormalUnit(), state, row, 7);
    }

    private static void setBoardSquare(BaseUnit unit, BoardSquareState state, int xPosition, int yPosition) {
        boardState[xPosition][yPosition] = state;
        boardUnits[xPosition][yPosition] = unit;
    }

    public static void movePreview(int xPosition, int yPosition) {
        if ((selectedXPosition == xPosition && selectedYPosition == yPosition)
                || (boardState[xPosition][yPosition] != currentPlayer)) {
            resetSelectedAndRerender();
        } else {
            boardPane.movePreview(xPosition, yPosition);
            selectedXPosition = xPosition;
            selectedYPosition = yPosition;
            setCurrentPlayerState(PlayerState.MOVE);
        }
    }

    public static void attackPreview(int xPosition, int yPosition) {
        if ((selectedXPosition == xPosition && selectedYPosition == yPosition)
                || (boardState[xPosition][yPosition] != currentPlayer)) {
            resetSelectedAndRerender();
        } else {
            boardPane.attackPreview(xPosition, yPosition);
            selectedXPosition = xPosition;
            selectedYPosition = yPosition;
            setCurrentPlayerState(PlayerState.ATTACK);
        }
    }

    public static void move(int xPosition, int yPosition) {
        boardState[xPosition][yPosition] = boardState[selectedXPosition][selectedYPosition];
        boardState[selectedXPosition][selectedYPosition] = BoardSquareState.EMPTY;
        boardUnits[xPosition][yPosition] = boardUnits[selectedXPosition][selectedYPosition];
        boardUnits[selectedXPosition][selectedYPosition] = null;
        toggleCurrentPlayer();
    }

    public static void attack(int xPosition, int yPosition) {
        BaseUnit selectedUnit = boardUnits[selectedXPosition][selectedYPosition];
        BaseUnit targetUnit = boardUnits[xPosition][yPosition];
        System.out.println("atkk" + selectedUnit);

        if (selectedUnit instanceof Attackable) {
            if (boardState[xPosition][yPosition] == currentPlayer)
                return;
            Attackable attacker = (Attackable) selectedUnit;
            attacker.attackUnit(targetUnit);
        }
        if (selectedUnit instanceof Debuffable) {
            if (boardState[xPosition][yPosition] == currentPlayer)
                return;
            Debuffable attacker = (Debuffable) selectedUnit;
            attacker.debuffUnit(targetUnit);
        }
        if (selectedUnit instanceof Buffable) {
            if (boardState[xPosition][yPosition] != currentPlayer)
                return;
            Buffable healer = (Buffable) selectedUnit;
            healer.buffUnit(targetUnit);
        }
        toggleCurrentPlayer();
    }

    public static void resetSelectedAndRerender() {
        // Remove dead unit
        for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
            for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
                if (boardUnits[i][j] != null && boardUnits[i][j].getCurrentHealth() <= 0) {
                    boardState[i][j] = BoardSquareState.EMPTY;
                    boardUnits[i][j] = null;
                }
            }
        }
        selectedXPosition = -1;
        selectedYPosition = -1;
        setCurrentPlayerState(PlayerState.NONE);
        boardPane.resetBoard();
    }

    public static void toggleCurrentPlayer() {
        resetSelectedAndRerender();
        GameLogic.setTimerActive(false);
        Timer.setTimer(TimeConstant.TIME_PER_TURN);
        if (currentPlayer == BoardSquareState.PLAYER1)
            GameLogic.currentPlayer = BoardSquareState.PLAYER2;
        else
            GameLogic.currentPlayer = BoardSquareState.PLAYER1;
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
                            gameOver();
                        }
                    });
                }
            } catch (InterruptedException e) {
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
    public static BoardSquareState[][] getBoardState() {
        return boardState;
    }

    public static BaseUnit[][] getBoardUnits() {
        return boardUnits;
    }

    public static BoardPane getBoardPane() {
        return boardPane;
    }

    public static BoardSquareState getCurrentPlayer() {
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

    public static PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public static void setCurrentPlayerState(PlayerState currentPlayerState) {
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
            startTimer();
        } else {
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
}
