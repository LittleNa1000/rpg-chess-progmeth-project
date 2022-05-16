package gui;

import base.BaseUnit;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StatsPane extends HBox {
  private ImageView image;
  private VBox detailsPane;
  private ImageView movePtrn;
  private ImageView atkPtrn;
  private BorderPane header;
  private Text name;
  private Text hp;
  private ProgressBar hBar;
  private HBox allStats;
  private Text mainStats;
  private Text debuffs;

  public void showStats(BaseUnit unit, int xPosition, int yPosition) {
    System.out.println(unit.getName());
    image.setImage(new Image(unit.getImageUrl(), 120, 120, true, false));
    movePtrn.setImage(new Image(unit.getImageUrl(), 120, 120, true, false));
    atkPtrn.setImage(new Image(unit.getImageUrl(), 120, 120, true, false));
    name.setText("[ " + String.valueOf(xPosition) + " , " + String
        .valueOf(yPosition) + " ] " + unit.getName());
    hp.setText(String.valueOf(unit.getCurrentHealth()) + " / " + String.valueOf(unit.getMaxHealth()));
    hBar.setPrefWidth(400);
    hBar.setPrefHeight(30);
    hBar.setProgress(((double) unit.getCurrentHealth()) / ((double) unit.getMaxHealth()));
    hBar.setVisible(true);
    String mainStatString = "Stats";
    mainStatString += "\nPower: " + String.valueOf(unit.getPower());
    mainStats.setText(mainStatString);
    String debuffsString = "Buffs/Debuffs";
    debuffsString += "\nStunned: " + String.valueOf(unit.getStunRoundLeft());
    debuffsString += "\nVenom: " + String.valueOf(unit.getVenomRoundLeft());
    debuffs.setText(debuffsString);
  }

  public StatsPane() {
    super();
    image = new ImageView();
    movePtrn = new ImageView();
    atkPtrn = new ImageView();
    detailsPane = new VBox();
    header = new BorderPane();
    name = new Text();
    hp = new Text();
    hBar = new ProgressBar();
    hBar.setVisible(false);
    allStats = new HBox();
    mainStats = new Text();
    debuffs = new Text();
    allStats.getChildren().addAll(mainStats, debuffs);
    header.setLeft(name);
    header.setRight(hp);
    detailsPane.getChildren().addAll(header, hBar, allStats);
    setPrefWidth(780);
    setSpacing(10);
    setAlignment(Pos.CENTER_LEFT);
    setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
    getChildren().addAll(image, detailsPane, movePtrn, atkPtrn);
  }
}
