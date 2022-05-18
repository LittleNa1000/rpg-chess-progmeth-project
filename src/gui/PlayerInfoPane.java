package gui;

import constant.BoardConstant;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.SquareOwnerState;
import util.StringUtil;

public class PlayerInfoPane extends VBox {
  private Text player;
  private Text name;
  private Text unitLeft;
  private ProgressBar pBar;
  private int unitCount;

  public void reduceUnit() {
    unitCount -= 1;
    unitLeft.setText("Units left: " + String.valueOf(unitCount));
    pBar.setProgress(((double) unitCount) / ((double) BoardConstant.TOTAL_UNIT));
  }

  public int getUnitCount() {
    return unitCount;
  }

  public PlayerInfoPane(SquareOwnerState state) {
    if (state == SquareOwnerState.PLAYER1) {
      player = new Text("Player 1");
      name = new Text(BoardConstant.PLAYER1_NAME);
      name.setStyle(StringUtil.getCss("-fx-fill: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
    } else {
      player = new Text("Player 2");
      name = new Text(BoardConstant.PLAYER2_NAME);
      name.setStyle(StringUtil.getCss("-fx-fill: " + BoardConstant.PLAYER2_HEALTH_BAR_COLOR + ";"));
    }
    unitCount = BoardConstant.TOTAL_UNIT;
    player.setFont(new Font(18));
    name.setFont(new Font(36));
    unitLeft = new Text("Units left: " + String.valueOf(unitCount));
    unitLeft.setFont(new Font(20));
    pBar = new ProgressBar(1);
    pBar.setPrefWidth(180);
    pBar.setPrefHeight(40);
    setPadding(new Insets(10, 10, 10, 10));
    setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, null, null)));
    getChildren().addAll(player, name, unitLeft, pBar);
  }
}
