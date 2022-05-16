package gui;

import java.util.Optional;

import constant.TimeConstant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameLogic;
import util.Timer;

public class StatusPane extends VBox {
  private Button quitBtn;
  private Button toggleTimerBtn;
  private Button skipTurnBtn;

  private void initSkipTurnBtn() {
    skipTurnBtn = new Button("Skip Turn");
    skipTurnBtn.setOnAction(e -> {
      GameLogic.setTimerActive(false);
      Timer.setTimer(TimeConstant.TIME_PER_TURN);
      GameLogic.setTimerActive(true);
    });
  }

  private void initToggleTimerBtn() {
    toggleTimerBtn = new Button("toggleTimer");
    toggleTimerBtn.setOnAction(e -> {
      GameLogic.setTimerActive(!GameLogic.isTimerActive());
    });
  }

  private void initQuitBtn() {
    quitBtn = new Button("Quit");
    quitBtn.setOnAction(e -> {
      Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit the game and return to main menu?",
          ButtonType.NO, ButtonType.YES);
      alert.setTitle("Quit Confirmation");
      alert.setHeaderText(null);
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.YES) {
        try {
          GameLogic.gameOver();
          Parent root = FXMLLoader.load(getClass().getResource("../application/resources/fxml/MainMenu.fxml"));
          Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          stage.setScene(new Scene(root));
          stage.show();
        } catch (Exception err) {
          err.printStackTrace();
        }
      }
    });
  }

  public StatusPane() {
    super();
    setPrefWidth(200);
    setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
    setSpacing(30);
    initQuitBtn();
    initToggleTimerBtn();
    initSkipTurnBtn();
    getChildren().addAll(toggleTimerBtn, skipTurnBtn, quitBtn);
  }
}
