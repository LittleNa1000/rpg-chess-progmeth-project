package gui;

import java.util.Optional;

import constant.BoardConstant;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.BoardSquareState;
import logic.GameLogic;

public class StatusPane extends VBox {
  private Button quitBtn;
  private Button toggleTimerBtn;
  private Button skipTurnBtn;
  private VBox displayCurrentTurn;
  private Text currentTurn;

  public void toggleTurn() {
    if (GameLogic.getCurrentPlayer() == BoardSquareState.PLAYER1) {
      currentTurn.setText(BoardConstant.PLAYER1_NAME);
      skipTurnBtn.setText("Skip to " + BoardConstant.PLAYER2_NAME + "'s Turn");
    } else {
      currentTurn.setText(BoardConstant.PLAYER2_NAME);
      skipTurnBtn.setText("Skip to " + BoardConstant.PLAYER1_NAME + "'s Turn");
    }
  }

  private void initSkipTurnBtn() {
    skipTurnBtn = new Button("Skip to " + BoardConstant.PLAYER2_NAME + "'s Turn");
    skipTurnBtn.setOnAction(e -> {
      GameLogic.toggleCurrentPlayer();
    });
  }

  private void initToggleTimerBtn() {
    toggleTimerBtn = new Button("Pause Timer");
    toggleTimerBtn.setOnAction(e -> {
      GameLogic.setTimerActive(!GameLogic.isTimerActive());
      if (GameLogic.isTimerActive()) {
        toggleTimerBtn.setText("Pause Timer");
      } else {
        toggleTimerBtn.setText("Resume Timer");
      }
    });
  }

  private void initQuitBtn() {
    quitBtn = new Button("Quit");
    quitBtn.setOnAction(e -> {
      Alert alert = new Alert(AlertType.CONFIRMATION, "Return to main menu?",
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
    displayCurrentTurn.setPrefHeight(100);
    displayCurrentTurn.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, null, null)));
    displayCurrentTurn.setAlignment(Pos.CENTER_LEFT);
    displayCurrentTurn.setPadding(new Insets(10, 10, 10, 10));
    currentTurn = new Text(BoardConstant.PLAYER1_NAME);
    currentTurn.setFont(new Font(36));
    Text text = new Text("Current Turn:");
    text.setFont(new Font(20));
    displayCurrentTurn.getChildren().addAll(text, currentTurn);
  }

  public StatusPane() {
    super();
    setPrefWidth(200);
    setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
    setSpacing(30);
    setAlignment(Pos.TOP_CENTER);
    initQuitBtn();
    initToggleTimerBtn();
    initSkipTurnBtn();
    initDisplayCurrentTurn();
    getChildren().addAll(displayCurrentTurn, toggleTimerBtn, skipTurnBtn, quitBtn);
  }
}
