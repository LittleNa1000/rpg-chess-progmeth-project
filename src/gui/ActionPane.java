package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ActionPane extends HBox {
  private TimerPane timerPane;
  private StatsPane statsPane;

  public ActionPane() {
    super();
    setPrefHeight(125);
    setTimerPane(new TimerPane());
    setStatsPane(new StatsPane());
    setAlignment(Pos.CENTER);
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
