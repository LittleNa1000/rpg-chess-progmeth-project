package gui;

import java.util.ArrayList;

import base.BaseUnit;
import constant.BoardConstant;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.PlayerState;
import unit.NormalUnit;

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
    BaseUnit[][] boardUnits = GameLogic.getBoardUnits();
    for (int i = 0; i < BoardConstant.ROW_NUMBER; i++)
      for (int j = 0; j < BoardConstant.COLOUMN_NUMBER; j++) {
        BoardSquare boardSquare;
        if (boardUnits[i][j] != null)
          boardSquare = new BoardSquare(i, j, boardUnits[i][j], PlayerState.NONE);
        else
          boardSquare = new BoardSquare(i, j, PlayerState.NONE);

        allSquares[i][j] = boardSquare;
        this.add(boardSquare, j, i);
      }
    // setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
  }

  public static void changeBackground(int xPosition, int yPosition, BaseUnit unit) {
    if (unit instanceof NormalUnit) {

    }
  }
}
