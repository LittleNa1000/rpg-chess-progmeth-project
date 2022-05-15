package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class MainController implements Initializable {
  @FXML
  private Text title;

  @Override
  public void initialize(URL location, ResourceBundle resourceBundle) {
    title.setText("setText works!");
  }
}
