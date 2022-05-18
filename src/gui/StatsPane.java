package gui;

import base.BaseUnit;
import constant.BoardConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.SquareOwnerState;
import logic.GameLogic;
import unit.FlyingUnit;
import unit.FreezerUnit;
import unit.HealerUnit;
import unit.VenomUnit;
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
  private Text ability;
  private Text debuffs;

  public void showStats(BaseUnit unit, int xPosition, int yPosition) {
    image.setImage(unit.getImage());
    movePtrn.setImage(unit.getMovePattern());
    atkPtrn.setImage(unit.getAtkPattern());
    name.setText("[" + String.valueOf(xPosition) + " , " + String
        .valueOf(yPosition) + "] " + unit.getName());
    double progress = ((double) unit.getCurrentHealth()) / ((double) unit.getMaxHealth());
    hp.setText("HP: " + String.valueOf(unit.getCurrentHealth()) + " / " + String.valueOf(unit.getMaxHealth()) + " ("
        + String.valueOf(Math.round(progress * 100)) + "%)");
    if (unit.getStunRoundLeft() > 0) {
      hBar.setEffect(new SepiaTone(0.75));
    } else {
      hBar.setEffect(new SepiaTone(0));
    }
    if (GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.PLAYER1) {
      hBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
    } else {
      hBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER2_HEALTH_BAR_COLOR + ";"));
    }
    hBar.setProgress(progress);
    hBar.setVisible(true);
    String mainStatString = "Stats:\nPower: " + String.valueOf(unit.getPower());
    mainStats.setText(mainStatString);
    String debuffsString = "Debuffs:     ";
    if (unit.getStunRoundLeft() > 0) {
      debuffsString += "\nFrozen: " + String.valueOf(unit.getStunRoundLeft());
    }
    if (unit.getVenomRoundLeft() > 0) {
      debuffsString += "\nPoisoned: " + String.valueOf(unit.getVenomRoundLeft());
    }
    debuffs.setText(debuffsString);
    String abilityString = "Skill: ";
    if (unit instanceof FreezerUnit) {
      abilityString += "Freeze an\nenemy for " + String.valueOf(((FreezerUnit) unit).getStunRound()) + " turn(s)";
    } else if (unit instanceof HealerUnit) {
      abilityString += "Heal an\nally for " + String.valueOf(((HealerUnit) unit).getHealingPoint()) + " hp";
    } else if (unit instanceof VenomUnit) {
      abilityString += "Deals " + String.valueOf((VenomUnit.getPoisonpower())) + "";
      abilityString += "\ndamage to an\nenemy for " + String.valueOf(((VenomUnit) unit).getPoisonRound()) + " turn(s)";
    } else if (unit instanceof FlyingUnit) {
      abilityString += "Dodge\nNormal Unit's\nAttack";
    } else {
      abilityString += "\nNone";
    }
    ability.setText(abilityString);
  }

  public StatsPane() {
    super();
    image = new ImageView();
    image.setFitWidth(125);
    image.setFitHeight(125);
    movePtrn = new ImageView();
    atkPtrn = new ImageView();
    detailsPane = new VBox();
    header = new BorderPane();
    name = new Text();
    name.setFont(new Font(20));
    hp = new Text();
    hp.setFont(new Font(18));
    hBar = new ProgressBar();
    hBar.setPrefWidth(360);
    hBar.setVisible(false);
    allStats = new HBox();
    mainStats = new Text();
    mainStats.setFont(new Font(16));
    debuffs = new Text();
    debuffs.setFont(new Font(16));
    ability = new Text();
    ability.setFont(new Font(16));
    allStats.setSpacing(40);
    allStats.getChildren().addAll(mainStats, debuffs, ability);
    header.setLeft(name);
    header.setRight(hp);
    detailsPane.setSpacing(5);
    detailsPane.getChildren().addAll(header, hBar, allStats);
    setPrefWidth(780);
    setSpacing(10);
    setPadding(new Insets(0, 0, 0, 15));
    setAlignment(Pos.CENTER_LEFT);
    setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
    getChildren().addAll(image, detailsPane, movePtrn, atkPtrn);
  }
}
