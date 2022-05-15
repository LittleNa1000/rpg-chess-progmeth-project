package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import util.Timer;
import constant.TimeConstant;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class TimerPane extends BorderPane {
  private Text time;

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
    setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
    setCenter(time);
  }

}
