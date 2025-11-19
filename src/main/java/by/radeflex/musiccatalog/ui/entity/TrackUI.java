package by.radeflex.musiccatalog.ui.entity;

import by.radeflex.musiccatalog.dto.TrackReadDto;
import by.radeflex.musiccatalog.service.TrackService;
import by.radeflex.musiccatalog.ui.window.SaveTrackWindowFactory;
import by.radeflex.musiccatalog.ui.controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class TrackUI extends HBox {
    private final MainController mainController;
    private final SaveTrackWindowFactory saveTrackWindowFactory = SaveTrackWindowFactory.getInstance();
    private final TrackService trackService = TrackService.getInstance();
    private final TrackReadDto track;

    private void editFunc() {
        saveTrackWindowFactory.create(mainController, this).show();
    }

    private Button buildEditBtn() {
        var editBtn = new Button("\uF304");
        editBtn.setOnAction(e -> editFunc());
        editBtn.setCursor(Cursor.HAND);
        editBtn.setBackground(Background.EMPTY);
        editBtn.setFont(Font.font("Font Awesome 7 Free Solid", 18.0));

        return editBtn;
    }

    private void deleteFunc() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Подтверждение удаления");
        alert.setTitle("Удаление трека");
        alert.setContentText("Вы действительно хотите удалить этот трек");
        var btn = alert.showAndWait();
        if (btn.isPresent() && !btn.get().getButtonData().isCancelButton()) {
            trackService.delete(track.id());
            mainController.deleteTrackUI(this);
        }
    }

    private Button buildDeleteBtn() {
        var deleteBtn = new Button("\uF1F8");
        deleteBtn.setOnAction(e -> deleteFunc());
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setBackground(Background.EMPTY);
        deleteBtn.setFont(Font.font("Font Awesome 7 Free Solid", 18.0));

        return deleteBtn;
    }

    private Label buildLabel(String text, double width, int fontSize) {
        var label = new Label(text);
        label.setPrefWidth(width);
        label.setFont(Font.font(fontSize));
        return label;
    }

    private void initialize() {
        super.setAlignment(Pos.CENTER_LEFT);

        super.getChildren().add(buildLabel(track.author(), 280, 18));
        super.getChildren().add(buildLabel(track.title(), 280, 18));
        super.getChildren().add(buildLabel(track.genre().toString(), 235, 18));
        super.getChildren().add(buildLabel(track.duration(), 180, 18));
        super.getChildren().add(buildLabel(track.extension().toString(), 110, 18));
        super.getChildren().add(buildEditBtn());
        super.getChildren().add(buildDeleteBtn());
    }

    public TrackReadDto getTrack() {
        return track;
    }

    public TrackUI(TrackReadDto track, MainController mainController) {
        this.mainController = mainController;
        this.track = track;

        initialize();
    }
}
