package gui;

import base.BasePotion;
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
    private ProgressBar hBar;
    private ImageView imageView;
    private boolean isFrozen;

    public BoardSquare(int x, int y, BaseUnit unit) {
        hBar = new ProgressBar();
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        // setStyle(StringUtil.getCss());
        // setStyle(StringUtil.getCss());
        setSquareState(SquarePreviewState.NONE);
        setxPosition(x);
        setyPosition(y);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(3));
        setUnit(unit);
        setOnClickHandler();
        setFrozen(false);
    }

    private void draw() {
        updateHpBar();
        this.getChildren().clear();
        imageView.setImage(unit.getImage());
        this.getChildren().addAll(hBar, imageView);
        this.setStyle(
                StringUtil.getCss(
                        "-fx-background-image: url('" + StringUtil.getImageUrl(
                                isFrozen ? "brick-freeze-bg.jpg" : "brick-bg.jpg")
                                + "');",
                        "-fx-background-size: 100% 100%;",
                        "-fx-background-position: center center;", "-fx-background-repeat: stretch;",
                        "-fx-border-color: white;"));
    }

    private void setBackgroundImage(String fileName) {
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + StringUtil.getImageUrl(fileName) + "');",
                        "-fx-background-size: 100% 100%;",
                        "-fx-border-color: white;"));
    }

    public void removeUnit() {
        this.getChildren().clear();
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + StringUtil.getImageUrl("brick-bg.jpg") + "');",
                        "-fx-background-size: 100% 100%;",
                        "-fx-border-color: white;"));
    }

    private void setOnClickHandler() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MouseButton button = event.getButton();
            if (GameLogic.getBoardUnits()[xPosition][yPosition] != null)
                GameLogic.getActionPane().getStatsPane().showUnitStats(GameLogic.getBoardUnits()[xPosition][yPosition],
                        xPosition, yPosition);
            else if (GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.POTION) {
                System.out.println("SHOW POTION");
                GameLogic.getActionPane().getStatsPane()
                        .showPotionStats(GameLogic.getBoardPotions()[xPosition][yPosition], xPosition, yPosition);
            }
            if (this.squareState == SquarePreviewState.MOVE
                    && (GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.EMPTY
                            || GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.POTION)) {
                GameLogic.move(xPosition, yPosition);
                return;
            }

            if (this.squareState == SquarePreviewState.ATTACK
                    && (GameLogic.getBoardState()[xPosition][yPosition] != GameLogic.getCurrentPlayer()
                            || (GameLogic.getBoardState()[xPosition][yPosition] == GameLogic.getCurrentPlayer()
                                    && GameLogic.getSelectedUnit() instanceof Buffable))
                    && GameLogic.getBoardState()[xPosition][yPosition] != SquareOwnerState.EMPTY
                    && GameLogic.getBoardState()[xPosition][yPosition] != SquareOwnerState.POTION) {
                GameLogic.attack(xPosition, yPosition);
                return;
            }

            if (button == MouseButton.PRIMARY) {
                System.out.println("CLICK PRIMARY" + xPosition + yPosition);
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

        if (isFrozen) {
            setBackgroundImage("brick-freeze-bg.jpg");
        } else if (squareState == SquarePreviewState.NONE) {
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
        else {
            if (unit.getStunRoundLeft() > 0)
                setFrozen(true);
            else
                setFrozen(false);
            draw();
        }
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
            hBar.setProgress(((double) unit.getCurrentHealth()) / ((double) unit.getMaxHealth()));
            if (GameLogic.getBoardState()[xPosition][yPosition] == SquareOwnerState.PLAYER1)
                hBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER1_HEALTH_BAR_COLOR + ";"));
            else
                hBar.setStyle(StringUtil.getCss("-fx-accent: " + BoardConstant.PLAYER2_HEALTH_BAR_COLOR + ";"));

            if (unit.getVenomRoundLeft() > 0)
                hBar.setEffect(new SepiaTone(0.75));
            else
                hBar.setEffect(new SepiaTone(0));

            hBar.setPrefWidth(70);
            hBar.setPrefHeight(12);
            hBar.setMinHeight(12);
        }
    }

    public void updateHpBar(BasePotion potion) {
        System.out.println("AGE  " + ((double) potion.getAge()) / ((double) potion.getMaxAge()));
        hBar.setProgress(((double) potion.getAge()) / ((double) potion.getMaxAge()));
        hBar.setStyle(StringUtil.getCss("-fx-accent: blue;"));
        hBar.setEffect(new SepiaTone(0));
        hBar.setPrefWidth(70);
        hBar.setPrefHeight(12);
        hBar.setMinHeight(12);
    }

    public void addPotion(BasePotion potion) {
        this.getChildren().clear();
        imageView.setImage(potion.getImage());
        updateHpBar(potion);
        this.getChildren().addAll(hBar, imageView);
    }

    public void setFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public boolean isFrozen() {
        return isFrozen;
    }
}
