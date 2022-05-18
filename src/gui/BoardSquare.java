package gui;

import base.BaseUnit;
import base.Buffable;
import constant.BoardConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.SepiaTone;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import logic.SquareOwnerState;
import logic.GameLogic;
import logic.SquarePreviewState;
import util.StringUtil;

public class BoardSquare extends VBox {
    private int xPosition;
    private int yPosition;
    private BaseUnit unit;
    private SquarePreviewState squareState;
    private ProgressBar hpBar;
    private ImageView imageView;

    public BoardSquare(int x, int y, BaseUnit unit) {
        hpBar = new ProgressBar();
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        setSquareState(SquarePreviewState.NONE);
        setxPosition(x);
        setyPosition(y);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(3));
        setUnit(unit);
        setOnClickHandler();
    }

    private void draw(BaseUnit unit) {
        updateHpBar();
        if (unit.getStunRoundLeft() > 0) {
            hpBar.setEffect(new SepiaTone(0.75));
        } else {
            hpBar.setEffect(new SepiaTone(0));
        }
        this.getChildren().clear();
        imageView.setImage(unit.getImage());
        this.getChildren().addAll(hpBar, imageView);
        this.setStyle(
                StringUtil.getCss(
                        "-fx-background-image: url('" + StringUtil.getImageUrl("brick-bg.jpg") + "');",
                        "-fx-background-size: 100% 100%;",
                        "-fx-background-position: center center;", "-fx-background-repeat: stretch;"));
    }

    private void setBackgroundImage(String fileName) {
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + StringUtil.getImageUrl(fileName) + "');",
                        "-fx-background-size: 100% 100%;"));
    }

    public void removeUnit() {
        this.getChildren().clear();
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + StringUtil.getImageUrl("brick-bg.jpg") + "');",
                        "-fx-background-size: 100% 100%;"));
    }

    private void setOnClickHandler() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MouseButton button = event.getButton();
            if (GameLogic.getBoardUnits()[xPosition][yPosition] != null)
                GameLogic.getActionPane().getStatsPane().showStats(GameLogic.getBoardUnits()[xPosition][yPosition],
                        xPosition, yPosition);
            if (button == MouseButton.PRIMARY) {
                System.out.println("CLICK PRIMARY" + xPosition + yPosition);
                if (this.squareState == SquarePreviewState.MOVE
                        && GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.EMPTY) {
                    GameLogic.move(xPosition, yPosition);
                } else if (this.squareState == SquarePreviewState.ATTACK
                        && (GameLogic.getBoardState()[xPosition][yPosition] != GameLogic.getCurrentPlayer()
                                || (GameLogic.getBoardState()[xPosition][yPosition] == GameLogic.getCurrentPlayer()
                                        && GameLogic.getSelectedUnit() instanceof Buffable))
                        && GameLogic.getBoardState()[xPosition][yPosition] != SquareOwnerState.EMPTY) {
                    GameLogic.attack(xPosition, yPosition);
                } else
                    GameLogic.movePreview(xPosition, yPosition);
            } else if (button == MouseButton.SECONDARY) {
                GameLogic.attackPreview(xPosition, yPosition);
                System.out.println("CLICK SECONDARY" + xPosition + yPosition);
            }
        });
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setSquareState(SquarePreviewState squareState) {
        this.squareState = squareState;
        if (squareState == SquarePreviewState.NONE) {
            setBackgroundImage("brick-bg.jpg");
        } else if (squareState == SquarePreviewState.MOVE) {
            setBackgroundImage("brick-moveable-bg.jpg");
        } else if (squareState == SquarePreviewState.ATTACK) {
            setBackgroundImage("brick-attackable-bg.jpg");
        }
    }

    public void setUnit(BaseUnit unit) {
        this.unit = unit;
        if (unit == null)
            removeUnit();
        else
            draw(unit);
    }

    public SquarePreviewState getSquareState() {
        return squareState;
    }

    public BaseUnit getUnit() {
        return unit;
    }

    public void updateUnit() {
        setUnit(GameLogic.getBoardUnits()[xPosition][yPosition]);
    }

    public void updateHpBar() {
        if (unit != null) {
            hpBar.setProgress(((double) unit.getCurrentHealth()) / ((double) unit.getMaxHealth()));
            if (GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.PLAYER1)
                hpBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
            else
                hpBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER2_HEALTH_BAR_COLOR + ";"));

            hpBar.setPrefWidth(70);
            hpBar.setPrefHeight(12);
            hpBar.setMinHeight(12);
        }
    }
}
