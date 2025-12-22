package by.radeflex.musiccatalog.ui.controller;

import by.radeflex.musiccatalog.database.entity.Extension;
import by.radeflex.musiccatalog.dto.TrackCreateEditDto;
import by.radeflex.musiccatalog.database.entity.Genre;
import by.radeflex.musiccatalog.dto.TrackReadDto;
import by.radeflex.musiccatalog.exception.ValidationException;
import by.radeflex.musiccatalog.service.TrackService;
import by.radeflex.musiccatalog.ui.entity.TrackUI;
import by.radeflex.musiccatalog.util.FileUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class SaveTrackController {
    private final TrackService trackService = TrackService.getInstance();
    private MainController mainController;
    private TrackUI target;
    private File file;

    @FXML
    private TextField authorField;
    @FXML
    private TextField titleField;
    @FXML
    private ChoiceBox<Genre> genreChoiceBox;
    @FXML
    private Button fileBtn;

    @FXML
    private Label authorErrorLabel;
    @FXML
    private Label titleErrorLabel;
    @FXML
    private Label fileErrorLabel;

    @FXML
    private void initialize() {
        genreChoiceBox.getItems().addAll(Genre.values());
        if (target != null) {
            titleField.setText(target.getTrack().title());
            authorField.setText(target.getTrack().author());
            genreChoiceBox.setValue(target.getTrack().genre());
            if (target != null) {
                file = target.getTrack().path().toFile();
                fileBtn.setText(file.getName());
            }
        } else {
            genreChoiceBox.setValue(Genre.CLASSIC);
        }
    }

    @FXML
    protected void saveTrack() {
        var trackDto = new TrackCreateEditDto(
                titleField.getText(),
                authorField.getText(),
                genreChoiceBox.getValue(),
                file);
        try {
            TrackReadDto trackReadDto;
            var path = FileUtils.save(file);
            if (target != null) {
                if (!path.equals(target.getTrack().path()))
                    FileUtils.delete(target.getTrack().path());
                trackReadDto = trackService.update(target.getTrack().id(), trackDto, path);
                mainController.updateTrackUI(target, trackReadDto);
            } else {
                trackReadDto = trackService.save(trackDto, path);
                mainController.addTrackUI(trackReadDto);
            }
            ((Stage) authorField.getScene().getWindow()).close();
            file = null;
        } catch (ValidationException e) {
            authorErrorLabel.setText(e.getErrors().get("author"));
            titleErrorLabel.setText(e.getErrors().get("title"));
            fileErrorLabel.setText(e.getErrors().get("file"));
        }
    }

    @FXML
    protected void cancel() {
        ((Stage) titleField.getScene().getWindow()).close();
    }

    @FXML
    public void chooseFile(Event event) {
        var formats = Arrays.stream(Extension.values())
                .map(Object::toString)
                .map(String::toLowerCase)
                .map(ext -> "*." + ext)
                .toArray(String[]::new);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add (
                new FileChooser.ExtensionFilter(Arrays.toString(formats),
                        formats)
        );

        fileChooser.setTitle("Выберите трек");
        var chosenFile = fileChooser.showOpenDialog(titleField.getScene().getWindow());
        if (chosenFile != null) {
            file = chosenFile;
            fileBtn.setText(file.getName());
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setTarget(TrackUI target) {
        this.target = target;
    }
}
