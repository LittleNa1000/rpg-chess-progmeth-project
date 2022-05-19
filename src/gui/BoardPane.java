package gui;

import base.BaseUnit;
import constant.BoardConstant;
import constant.ColorConstant;
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
import util.StringUtil;

public class BoardPane extends GridPane {
  private BoardSquare[][] allSquares = new BoardSquare[BoardConstant.ROW_NUMBER][BoardConstant.COLOUMN_NUMBER];

  public BoardPane() {
    super();
    GameLogic.init();
    setPadding(new Insets(5));
    setStyle(StringUtil.getCss("-fx-border-color: " + ColorConstant.PALETTE_4 + ";",
        "-fx-border-width: 4;", "-fx-background-color: " + ColorConstant.PALETTE_1 + ";"));

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
      allSquares[xPosition][yPosition].setSquareState(state);
    }
  }

  public void resetAllPreviewState() {
    for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
      for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
        allSquares[i][j].setSquareState(SquarePreviewState.NONE);
      }
  }

  public BoardSquare[][] getAllSquares() {
    return allSquares;
  }
}
