package logic;

import base.BaseUnit;
import constant.BoardConstant;
import gui.BoardPane;
import unit.NormalUnit;

public class GameLogic {
    private static int currentPlayer = 0;
    private static int selectedXPosition = -1;
    private static int selectedYPosition = -1;
    private static BoardPane boardPane;
    private static BoardSquareState[][] boardState = new BoardSquareState[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];
    private static BaseUnit[][] boardUnits = new BaseUnit[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];

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
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 0);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 1);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 2);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 3);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 4);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 5);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 6);
        setBoardSquare(new NormalUnit(), BoardSquareState.PLAYER1, row, 7);
    }

    private static void setBoardSquare(BaseUnit unit, BoardSquareState state, int xPosition, int yPosition) {
        boardState[xPosition][yPosition] = state;
        boardUnits[xPosition][yPosition] = unit;
    }

    public static void movePreview(int xPosition, int yPosition) {
        boardPane.resetBoard();
        if (selectedXPosition == xPosition && selectedYPosition == yPosition) {
            selectedXPosition = -1;
            selectedYPosition = -1;
        } else {
            boardPane.movePreview(xPosition, yPosition);
            selectedXPosition = xPosition;
            selectedYPosition = yPosition;
        }
    }

    public static void attackPreview(int xPosition, int yPosition) {
        boardPane.resetBoard();
        if (selectedXPosition == xPosition && selectedYPosition == yPosition) {
            selectedXPosition = -1;
            selectedYPosition = -1;
        } else {
            boardPane.attackPreview(xPosition, yPosition);
            selectedXPosition = xPosition;
            selectedYPosition = yPosition;
        }
    }

    public static BoardSquareState[][] getBoardState() {
        return boardState;
    }

    public static BaseUnit[][] getBoardUnits() {
        return boardUnits;
    }

    public static BoardPane getBoardPane() {
        return boardPane;
    }

    public static int getCurrentPlayer() {
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

}
