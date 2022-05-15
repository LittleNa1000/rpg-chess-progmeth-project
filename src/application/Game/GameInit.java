package application.Game;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class GameInit {
  private static Stage stage;
  private static Scene scene;

  public static void init(ActionEvent e) {
    BorderPane root = new BorderPane();
    root.setPrefSize(980, 720);
    root.setRight(new StatusPane());
    root.setTop(new ActionPane());
    root.setCenter(new Board());
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
