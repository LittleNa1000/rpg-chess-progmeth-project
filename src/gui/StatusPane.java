package gui;

import java.util.Optional;

import constant.BoardConstant;
import constant.ColorConstant;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SquareOwnerState;
import logic.GameLogic;
import util.StringUtil;

public class StatusPane extends VBox {
  private Button quitBtn;
  private Button toggleTimerBtn;
  private Button skipTurnBtn;
  private VBox displayCurrentTurn;
  private Text currentTurn;
  private PlayerInfoPane player1Pane;
  private PlayerInfoPane player2Pane;
  private Label turnCount;

  public Button getToggleTimerBtn() {
    return toggleTimerBtn;
  }

  public Button getSkipTurnBtn() {
    return skipTurnBtn;
  }

  public void reduceUnit(SquareOwnerState state) {
    if (state == SquareOwnerState.PLAYER1) {
      player1Pane.reduceUnit();
    } else if (state == SquareOwnerState.PLAYER2) {
      player2Pane.reduceUnit();
    }
    if (player1Pane.getUnitCount() <= 0 && player2Pane.getUnitCount() <= 0) {
      GameLogic.setWinner(SquareOwnerState.EMPTY);
    } else if (player1Pane.getUnitCount() <= 0) {
      GameLogic.setWinner(SquareOwnerState.PLAYER2);
    } else if (player2Pane.getUnitCount() <= 0) {
      GameLogic.setWinner(SquareOwnerState.PLAYER1);
    }
  }

  public void toggleTurn() {
    if (GameLogic.getCurrentPlayer() == SquareOwnerState.PLAYER1) {
      currentTurn.setText(BoardConstant.PLAYER1_NAME);
      currentTurn.setStyle(StringUtil.getCss("-fx-fill: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
    } else {
      currentTurn.setText(BoardConstant.PLAYER2_NAME);
      currentTurn.setStyle(StringUtil.getCss("-fx-fill: " + BoardConstant.PLAYER2_HEALTH_BAR_COLOR + ";"));
    }
    turnCount.setText("Turn " + String.valueOf(GameLogic.getRoundCounter()));
  }

  private void initSkipTurnBtn() {
    skipTurnBtn = new Button("Skip Turn");
    skipTurnBtn.setFont(new Font(24));
    skipTurnBtn.setWrapText(true);
    skipTurnBtn.setOnAction(e -> {
      GameLogic.toggleCurrentPlayer();
    });
  }

  private void initToggleTimerBtn() {
    toggleTimerBtn = new Button("Pause Timer");
    toggleTimerBtn.setFont(new Font(24));
    toggleTimerBtn.setOnAction(e -> {
      GameLogic.setTimerActive(!GameLogic.isTimerActive());
    });
  }

  private void initQuitBtn() {
    quitBtn = new Button("Quit");
    quitBtn.setFont(new Font(24));
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
    displayCurrentTurn.setStyle(StringUtil.getCss("-fx-background-color: " + ColorConstant.PALETTE_1 + ";"));
    currentTurn = new Text(BoardConstant.PLAYER1_NAME);
    currentTurn.setStyle(StringUtil.getCss("-fx-fill: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
    currentTurn.setFont(new Font(36));
    turnCount = new Label("Turn 0");
    turnCount.setPrefWidth(200);
    turnCount.setAlignment(Pos.CENTER);
    turnCount.setFont(new Font(18));
    Text text = new Text("Current turn:");
    text.setFont(new Font(22));
    displayCurrentTurn.getChildren().addAll(turnCount, text, currentTurn);
  }

  public StatusPane() {
    super();
    setPrefWidth(200);
    setAlignment(Pos.BOTTOM_CENTER);
    initQuitBtn();
    initToggleTimerBtn();
    initSkipTurnBtn();
    initDisplayCurrentTurn();
    VBox btnPane = new VBox();
    btnPane.setSpacing(10);
    btnPane.setAlignment(Pos.CENTER);
    btnPane.getChildren().addAll(toggleTimerBtn, skipTurnBtn, quitBtn);
    player1Pane = new PlayerInfoPane(SquareOwnerState.PLAYER1);
    player2Pane = new PlayerInfoPane(SquareOwnerState.PLAYER2);
    setStyle(StringUtil.getCss("-fx-background-color: " + ColorConstant.PALETTE_3 + ";"));
    getChildren().addAll(displayCurrentTurn, btnPane, player1Pane, player2Pane);
    setMargin(btnPane, new Insets(20, 0, 20, 0));
  }
}
