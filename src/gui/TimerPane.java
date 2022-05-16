package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.Timer;
import constant.TimeConstant;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class TimerPane extends BorderPane {
  private Text time;
  private Text timeLeft;

  public Text getTime() {
    return this.time;
  }

  public void setTime() {
    time.setText(Timer.getString());
  }

  public TimerPane() {
    super();
    setPrefWidth(200);
    Timer.setTimer(TimeConstant.TIME_PER_TURN);
    time = new Text(Timer.getString());
    time.setFont(new Font(36));
    time.setFill(Color.WHITE);
    timeLeft = new Text("Time Left");
    timeLeft.setFont(new Font(20));
    timeLeft.setFill(Color.WHITE);
    setAlignment(timeLeft, Pos.CENTER);
    setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
    setTop(timeLeft);
    setCenter(time);
  }

}
