package by.radeflex.musiccatalog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(
                getClass().getResourceAsStream(
                        "/by/radeflex/musiccatalog/fonts/Font Awesome 7 Free-Solid-900.otf"
                ),
                15
        );
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Музыкальный каталог");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}