package application.Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StatusPane extends VBox {
  public StatusPane() {
    super();
    setPrefWidth(200);
    setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
    Button quitBtn = new Button("Quit");
    quitBtn.setOnAction(e -> {
      try {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ;
        stage.setScene(new Scene(root));
        stage.show();
      } catch (Exception err) {
        err.printStackTrace();
      }
    });
    getChildren().add(quitBtn);
  }
}
