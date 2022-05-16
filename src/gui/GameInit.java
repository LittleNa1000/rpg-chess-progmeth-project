package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.GameUtil;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class GameInit {
  private static Stage stage;
  private static Scene scene;

  public static void init(ActionEvent e) {
    BorderPane root = new BorderPane();
    TimerPane timerPane = new TimerPane();
    root.setPrefSize(980, 720);
    root.setRight(new StatusPane());
    root.setTop(new ActionPane(timerPane));
    root.setCenter(new BoardPane());
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    GameUtil.startTimer(timerPane);
  }
}
