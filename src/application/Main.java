package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/MainMenu.fxml"));
            stage.setTitle("RPG Chess");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream(
                    "resources/normal-unit-2.png")));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
