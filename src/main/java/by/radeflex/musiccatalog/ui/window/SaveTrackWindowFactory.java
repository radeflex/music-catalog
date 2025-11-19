package by.radeflex.musiccatalog.ui.window;

import by.radeflex.musiccatalog.ui.controller.MainController;
import by.radeflex.musiccatalog.ui.controller.SaveTrackController;
import by.radeflex.musiccatalog.ui.entity.TrackUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveTrackWindowFactory {
    private static final SaveTrackWindowFactory INSTANCE = new SaveTrackWindowFactory();

    private SaveTrackWindowFactory() {}

    public static SaveTrackWindowFactory getInstance() {
        return INSTANCE;
    }

    public Stage create(MainController mainController, TrackUI target) {
        try {
            FXMLLoader loader = new FXMLLoader(SaveTrackWindowFactory.class.getResource("/by/radeflex/musiccatalog/save-track.fxml"));
            SaveTrackController stController = new SaveTrackController();
            stController.setMainController(mainController);
            stController.setTarget(target);
            loader.setController(stController);
            VBox root = loader.load();

            var window = new Stage();
            window.setScene(new Scene(root));
            window.setTitle("Сохранение трека");
            return window;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
