package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.StringUtil;
import util.TimerUtil;
import constant.ColorConstant;
import constant.TimeConstant;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class TimerPane extends BorderPane {
  private Text time;
  private Text timeLeft;

  public Text getTime() {
    return this.time;
  }

  public void setTime() {
    time.setText(TimerUtil.getString());
  }

  public TimerPane() {
    super();
    setPrefWidth(200);
    TimerUtil.setTimer(TimeConstant.TIME_PER_TURN);
    time = new Text(TimerUtil.getString());
    time.setFont(new Font(36));
    time.setFill(Color.WHITE);
    timeLeft = new Text("Time Left");
    timeLeft.setFont(new Font(20));
    timeLeft.setFill(Color.WHITE);
    setAlignment(timeLeft, Pos.CENTER);
    setStyle(StringUtil.getCss("-fx-background-color: " + ColorConstant.PALETTE_4 + ";"));
    setTop(timeLeft);
    setCenter(time);
  }

}
