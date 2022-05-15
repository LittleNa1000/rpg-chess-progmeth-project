package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MainController implements Initializable {
  private String[] heading = { "Objectives", "How to Play" };
  private String[] description = { "Win the opponent", "Just play" };
  private String[] image = { "objective.png", "how-to.png" };
  private int idx = 0;

  @FXML
  private Text headingInfo;
  @FXML
  private Text descriptionInfo;
  @FXML
  private Text page;
  @FXML
  private ImageView imageInfo;

  private void loadInfo() {
    imageInfo.setImage(new Image(getClass().getResourceAsStream("resources/images/" + image[idx])));
    headingInfo.setText(heading[idx]);
    descriptionInfo.setText(description[idx]);
    page.setText("Page " + String.valueOf(idx + 1) + " of "
        + String.valueOf(heading.length));
  }

  @Override
  public void initialize(URL location, ResourceBundle resourceBundle) {
    loadInfo();
  }

  public void backInfo(ActionEvent e) {
    if (idx == 0) {
      idx = heading.length;
    }
    idx -= 1;
    loadInfo();
  }

  public void nextInfo(ActionEvent e) {
    idx += 1;
    idx %= heading.length;
    loadInfo();
  }

  public void exit(ActionEvent e) {
    Platform.exit();
  }
}
