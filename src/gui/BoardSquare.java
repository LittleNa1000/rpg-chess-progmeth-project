package gui;

import base.BaseUnit;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.StringUtil;

public class BoardSquare extends Pane {
    private int xPosition;
    private int yPosition;

    private String url;

    public BoardSquare(int x, int y) {
        setxPosition(x);
        setyPosition(y);
    }

    public BoardSquare(int x, int y, BaseUnit unit) {

        setxPosition(x);
        setyPosition(y);
        this.draw(unit.getImageUrl(), "#f5f5dc");
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {

                    System.out.println("CLICK PRIMARY" + xPosition + yPosition);

                } else if (button == MouseButton.SECONDARY) {
                    System.out.println("CLICK SECONDARY" + xPosition + yPosition);
                }
            }
        });
    }

    private void draw(String url, String color) {
        this.setStyle(
                StringUtil.getCss("-fx-background-image: url('" + url + "');", "-fx-background-size: 10 10;",
                        "-fx-background-position: center center;", "-fx-background-repeat: stretch;",
                        "-fx-background-color: " + color));
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
