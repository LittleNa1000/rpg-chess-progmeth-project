package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ActionPane extends HBox {
  private TimerPane timerPane;
  private StatsPane statsPane;

  public ActionPane() {
    super();
    setPrefHeight(125);
    timerPane = new TimerPane();
    statsPane = new StatsPane();
    setAlignment(Pos.CENTER);
    setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
    getChildren().addAll(statsPane, timerPane);
  }

  public void setTimerPane(TimerPane tPane) {
    timerPane = tPane;
  }

  public TimerPane getTimerPane() {
    return timerPane;
  }

  public void setStatsPane(StatsPane sPane) {
    statsPane = sPane;
  }

  public StatsPane getStatsPane() {
    return statsPane;
  }
}
