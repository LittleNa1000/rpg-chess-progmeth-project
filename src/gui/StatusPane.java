package gui;

import java.util.Optional;

import constant.BoardConstant;
import constant.TimeConstant;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.BoardSquareState;
import logic.GameLogic;
import util.Timer;

public class StatusPane extends VBox {
  private Button quitBtn;
  private Button toggleTimerBtn;
  private Button skipTurnBtn;
  private VBox displayCurrentTurn;
  private Text currentTurn;

  public void toggleTurn() {
    if (GameLogic.getCurrentPlayer() == BoardSquareState.PLAYER1) {
      currentTurn.setText(BoardConstant.PLAYER1_NAME);
    } else {
      currentTurn.setText(BoardConstant.PLAYER2_NAME);
    }
  }

  private void initSkipTurnBtn() {
    skipTurnBtn = new Button("Skip Turn");
    skipTurnBtn.setOnAction(e -> {
      GameLogic.setTimerActive(false);
      Timer.setTimer(TimeConstant.TIME_PER_TURN);
      GameLogic.toggleCurrentPlayer();
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

  private void initDisplayCurrentTurn() {
    displayCurrentTurn = new VBox();
    displayCurrentTurn.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, null, null)));
    currentTurn = new Text(BoardConstant.PLAYER1_NAME);
    displayCurrentTurn.getChildren().addAll(new Text("Current Turn"), currentTurn);
  }

  public StatusPane() {
    super();
    setPrefWidth(200);
    setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
    setSpacing(30);
    setAlignment(Pos.CENTER);
    initQuitBtn();
    initToggleTimerBtn();
    initSkipTurnBtn();
    initDisplayCurrentTurn();
    getChildren().addAll(displayCurrentTurn, toggleTimerBtn, skipTurnBtn, quitBtn);
  }
}
