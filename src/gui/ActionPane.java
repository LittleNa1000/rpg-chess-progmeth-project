package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ActionPane extends HBox {
  public ActionPane() {
    super();
    setPrefHeight(125);
    setAlignment(Pos.CENTER);
    setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
  }
}
