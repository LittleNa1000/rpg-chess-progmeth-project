package application.Game;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class Board extends GridPane {
  private final int numCols = 8;
  private final int numRows = 8;

  public Board() {
    super();
    setPadding(new Insets(15, 15, 15, 15));
    setGridLinesVisible(true);
    for (int i = 0; i < numCols; i++) {
      ColumnConstraints colConst = new ColumnConstraints();
      colConst.setPercentWidth(100.0 / numCols);
      getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConst = new RowConstraints();
      rowConst.setPercentHeight(100.0 / numRows);
      getRowConstraints().add(rowConst);
    }
    setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
  }
}
