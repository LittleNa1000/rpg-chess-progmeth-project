package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StatsPane extends HBox {
  private ImageView image;
  private VBox detailsPane;
  private ImageView movePtrn;
  private ImageView atkPtrn;
  private BorderPane header;
  private Text name;
  private Text hp;
  private Rectangle hBar;
  private HBox allStats;
  private Text mainStats;
  private Text debuffs;

  public void showStats() {
    System.out.println("SHOW");
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
    hBar = new Rectangle();
    allStats = new HBox();
    mainStats = new Text();
    debuffs = new Text();
    allStats.getChildren().addAll(mainStats, debuffs);
    header.setLeft(name);
    header.setRight(hp);
    detailsPane.getChildren().addAll(header, hBar, allStats);
    getChildren().addAll(image, detailsPane, movePtrn, atkPtrn);
  }
}
