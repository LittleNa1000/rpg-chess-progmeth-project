package application.Game;

import constant.BoardConstant;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class Board extends GridPane {
  public Board() {
    super();
    setPadding(new Insets(15, 15, 15, 15));
    setGridLinesVisible(true);
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
    setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
  }
}
