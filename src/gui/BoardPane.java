package gui;

import base.BasePotion;
import base.BaseUnit;
import constant.BoardConstant;

import constant.UnitConstant;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.GameLogic;
import logic.SquarePreviewState;
import unit.FlyingUnit;
import unit.FreezerUnit;
import unit.HealerUnit;
import unit.NormalUnit;
import unit.ShooterUnit;
import unit.VenomUnit;

public class BoardPane extends GridPane {
  private BoardSquare[][] allSquares = new BoardSquare[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];

  public BoardPane() {
    super();
    GameLogic.init();
    // setHgap(8);
    // setVgap(8);
    setPadding(new Insets(8));

    // setPadding(new Insets(15, 15, 15, 15));
    setGridLinesVisible(true);
    // setBorder(new Border(
    // new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
    // BorderWidths.DEFAULT)));

    for (int i = 0; i < BoardConstant.COLOUMN_NUMBER; i++) {
      ColumnConstraints colConst = new ColumnConstraints();
      colConst.setPercentWidth(100.0 / BoardConstant.COLOUMN_NUMBER);
      getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < BoardConstant.ROW_NUMBER; i++) {
      RowConstraints rowConst = new RowConstraints();
      rowConst.setPercentHeight(100.0 / BoardConstant.ROW_NUMBER);
      getRowConstraints().add(rowConst);
    }
    initBoard();
    // setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
  }

  private void initBoard() {
    BaseUnit[][] boardUnits = GameLogic.getBoardUnits();
    for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
      for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
        BoardSquare boardSquare = new BoardSquare(i, j, boardUnits[i][j]);
        allSquares[i][j] = boardSquare;
        this.add(boardSquare, j, i);
      }
  }

  public void movePreview(int unitX, int unitY) {
    BaseUnit[][] boardUnits = GameLogic.getBoardUnits();
    BaseUnit unit = boardUnits[unitX][unitY];
    int[][] directionArrays;
    if (unit instanceof NormalUnit)
      directionArrays = UnitConstant.NORMAL_UNIT_MOVE;
    else if (unit instanceof FlyingUnit)
      directionArrays = UnitConstant.FLYING_UNIT_MOVE;
    else if (unit instanceof ShooterUnit)
      directionArrays = UnitConstant.SHOOTER_UNIT_MOVE;
    else if (unit instanceof VenomUnit)
      directionArrays = UnitConstant.VENOM_UNIT_MOVE;
    else if (unit instanceof HealerUnit)
      directionArrays = UnitConstant.HEALER_UNIT_MOVE;
    else if (unit instanceof FreezerUnit)
      directionArrays = UnitConstant.FREEZER_UNIT_MOVE;
    else
      return;
    previewHelper(directionArrays, SquarePreviewState.MOVE, unitX, unitY);
  }

  public void attackPreview(int unitX, int unitY) {
    BaseUnit[][] boardUnits = GameLogic.getBoardUnits();
    BaseUnit unit = boardUnits[unitX][unitY];
    int[][] directionArrays;
    if (unit instanceof NormalUnit)
      directionArrays = UnitConstant.NORMAL_UNIT_ATTACK;
    else if (unit instanceof FlyingUnit)
      directionArrays = UnitConstant.FLYING_UNIT_ATTACK;
    else if (unit instanceof ShooterUnit)
      directionArrays = UnitConstant.SHOOTER_UNIT_ATTACK;
    else if (unit instanceof VenomUnit)
      directionArrays = UnitConstant.VENOM_UNIT_ATTACK;
    else if (unit instanceof HealerUnit)
      directionArrays = UnitConstant.HEALER_UNIT_HEAL_RANGE;
    else if (unit instanceof FreezerUnit)
      directionArrays = UnitConstant.FREEZER_UNIT_FREEZE_RANGE;
    else
      return;
    previewHelper(directionArrays, SquarePreviewState.ATTACK, unitX, unitY);
  }

  private void previewHelper(int[][] directionArrays, SquarePreviewState state, int unitX, int unitY) {
    for (int i = 0; i < directionArrays.length; i++) {
      int xPosition = unitX + directionArrays[i][0];
      int yPosition = unitY + directionArrays[i][1];
      if (xPosition < 0 || xPosition >= BoardConstant.ROW_NUMBER || yPosition < 0
          || yPosition >= BoardConstant.COLOUMN_NUMBER)
        continue;
      // this.getChildren().remove(allSquares[xPosition][yPosition]);
      // BoardSquare boardSquare;
      // if (boardUnits[xPosition][yPosition] != null)
      // boardSquare = new BoardSquare(xPosition, yPosition,
      // boardUnits[xPosition][yPosition], state);
      // else
      // boardSquare = new BoardSquare(xPosition, yPosition, state);
      // this.add(boardSquare, yPosition, xPosition);

      allSquares[xPosition][yPosition].setSquareState(state);

    }
  }

  // public void move(int xPosition, int yPosition) {
  // allSquares[GameLogic.getSelectedXPosition()][GameLogic.getSelectedYPosition()].setUnit(null);
  // allSquares[xPosition][yPosition].setUnit(GameLogic.getBoardUnits()[xPosition][yPosition]);
  // }

  public void resetAllPreviewState() {
    for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
      for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
        allSquares[i][j].setSquareState(SquarePreviewState.NONE);
      }
  }

  public void updateUnit(int xPosition, int yPosition) {
    allSquares[xPosition][yPosition].updateUnit();
  }

  public BoardSquare[][] getAllSquares() {
    return allSquares;
  }

  public void generatePotion(int xPosition, int yPosition, BasePotion potion) {
    allSquares[xPosition][yPosition].addPotion(potion);
  }
}
