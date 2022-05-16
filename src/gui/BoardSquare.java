package gui;

import base.BaseUnit;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.PlayerState;
import util.StringUtil;

public class BoardSquare extends VBox {
    private int xPosition;
    private int yPosition;

    public BoardSquare(int x, int y, PlayerState state) {
        setxPosition(x);
        setyPosition(y);
        if (state == PlayerState.MOVE) {
            draw("#33FF8A");
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                GameLogic.move(xPosition, yPosition);
            });
        } else if (state == PlayerState.ATTACK) {
            draw("#FFB233");
        } else {
            draw("#FFEED1");
        }
    }

    public BoardSquare(int x, int y, BaseUnit unit, PlayerState state) {
        setxPosition(x);
        setyPosition(y);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(3));
        if (state == PlayerState.MOVE) {
            draw(unit, "#33FF8A");
        } else if (state == PlayerState.ATTACK) {
            draw(unit, "#FFB233");
        } else {
            draw(unit, "#FFEED1");
        }
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MouseButton button = event.getButton();
            if (button == MouseButton.PRIMARY) {
                GameLogic.movePreview(xPosition, yPosition);
                System.out.println("CLICK PRIMARY" + xPosition + yPosition);
            } else if (button == MouseButton.SECONDARY) {
                GameLogic.attackPreview(xPosition, yPosition);

                System.out.println("CLICK SECONDARY" + xPosition + yPosition);
            }
        });

    }

    private void draw(BaseUnit unit, String color) {
        this.getChildren().add(new Text("HP: " + unit.getCurrentHealth()));
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + unit.getImageUrl() + "');",
                        "-fx-background-size: 75% 75%;",
                        "-fx-background-position: center center;", "-fx-background-repeat: stretch;",
                        "-fx-background-color: " + color + ";"));
    }

    private void draw(String color) {
        this.setStyle(
                StringUtil.getCss("-fx-background-color: " + color));
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
}
