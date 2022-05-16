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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.StringUtil;

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
    image.setImage(new Image(unit.getImageUrl(), 125, 125, false, false));
    movePtrn.setImage(new Image(StringUtil.getImageUrl("normal-unit-move.jpg"), 125, 125, false, false));
    atkPtrn.setImage(new Image(StringUtil.getImageUrl("normal-unit-attack.jpg"), 125, 125, false, false));
    name.setText("[" + String.valueOf(xPosition) + " , " + String
        .valueOf(yPosition) + "] " + unit.getName());
    double progress = ((double) unit.getCurrentHealth()) / ((double) unit.getMaxHealth());
    hp.setText("HP: " + String.valueOf(unit.getCurrentHealth()) + " / " + String.valueOf(unit.getMaxHealth()) + " ("
        + String.valueOf(Math.round(progress * 100)) + "%)");
    hBar.setProgress(progress);
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
    name.setFont(new Font(20));
    hp = new Text();
    hp.setFont(new Font(18));
    hBar = new ProgressBar();
    hBar.setPrefWidth(350);
    hBar.setStyle(StringUtil.getCss("-fx-accent: green;"));
    hBar.setVisible(false);
    allStats = new HBox();
    mainStats = new Text();
    mainStats.setFont(new Font(16));
    debuffs = new Text();
    debuffs.setFont(new Font(16));
    allStats.setSpacing(50);
    allStats.getChildren().addAll(mainStats, debuffs);
    header.setLeft(name);
    header.setRight(hp);
    detailsPane.setSpacing(5);
    detailsPane.getChildren().addAll(header, hBar, allStats);
    setPrefWidth(780);
    setSpacing(10);
    setAlignment(Pos.CENTER_LEFT);
    setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
    getChildren().addAll(image, detailsPane, movePtrn, atkPtrn);
  }
}
