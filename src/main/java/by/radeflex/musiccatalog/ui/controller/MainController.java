package by.radeflex.musiccatalog.ui.controller;

import by.radeflex.musiccatalog.dto.TrackReadDto;
import by.radeflex.musiccatalog.lib.MyLinkedList;
import by.radeflex.musiccatalog.service.TrackService;
import by.radeflex.musiccatalog.ui.window.SaveTrackWindowFactory;
import by.radeflex.musiccatalog.ui.entity.TrackUI;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Comparator;

public class MainController {
    private final SaveTrackWindowFactory saveTrackWindowFactory = SaveTrackWindowFactory.getInstance();
    private final MyLinkedList<TrackReadDto> tracks = TrackService.getInstance().findAll();
    private Object lastSort;
    private int clickCount;

    @FXML
    private VBox trackTable;
    @FXML
    private TextField searchField;
    @FXML
    private Label searchResultLabel;
    @FXML
    private Button searchButton;
    @FXML
    private Button clearButton;

    private void render(MyLinkedList<TrackReadDto> tracks) {
        trackTable.getChildren().clear();
        for (var track : tracks) {
            trackTable.getChildren().add(new TrackUI(track, this));
        }
        clearButton.setVisible(false);
    }

    @FXML
    private void initialize() {
        render(tracks);
    }

    @FXML
    protected void createTrack() {
        saveTrackWindowFactory.create(this, null).show();
    }

    @FXML
    public void searchTrack() {
        var searchText = searchField.getText();
        if (!searchText.isEmpty()) {
            var filtered = tracks.filter(t -> t.author().contains(searchText));
            searchResultLabel.setText("Результатов: "+filtered.size());
            render(filtered);
            searchButton.setVisible(false);
            clearButton.setVisible(true);
        }
    }

    @FXML
    public void clearSearch() {
        searchField.setText("");
        searchResultLabel.setText("");
        render(tracks);
        searchButton.setVisible(true);
        clearButton.setVisible(false);
    }

    void updateTrackUI(TrackUI target, TrackReadDto track) {
        var index = trackTable.getChildren().indexOf(target);
        tracks.put(index, track);
        trackTable.getChildren().set(index, new TrackUI(track, this));
    }

    void addTrackUI(TrackReadDto trackReadDto) {
        tracks.add(trackReadDto);
        trackTable.getChildren().add(new TrackUI(trackReadDto, this));
    }

    public void deleteTrackUI(TrackUI trackUI) {
        tracks.remove(trackUI.getTrack());
        trackTable.getChildren().remove(trackUI);
    }

    @FXML
    public void sortByTitle(Event event) {
        var eventBtn = event.getSource();
        if (lastSort != null && !lastSort.equals(eventBtn)) {
            clickCount = 0;
        }
        switch (clickCount) {
            case 0:
                render(tracks.sorted(Comparator.comparing(TrackReadDto::title)));
                break;
            case 1:
                render(tracks.sorted((a, b) -> b.title().compareTo(a.title())));
                break;
            case 2:
                render(tracks);
                break;
        }
        clickCount++;
        clickCount %= 3;
        lastSort = eventBtn;
    }

    @FXML
    public void sortByAuthor(Event event) {
        var eventBtn = event.getSource();
        if (lastSort != null && !lastSort.equals(eventBtn)) {
            clickCount = 0;
        }
        switch (clickCount) {
            case 0:
                render(tracks.sorted(Comparator.comparing(TrackReadDto::author)));
                break;
            case 1:
                render(tracks.sorted((a, b) -> b.author().compareTo(a.author())));
                break;
            case 2:
                render(tracks);
                break;
        }
        clickCount++;
        clickCount %= 3;
        lastSort = eventBtn;
    }

    @FXML
    public void sortByGenre(Event event) {
        var eventBtn = event.getSource();
        if (lastSort != null && !lastSort.equals(eventBtn)) {
            clickCount = 0;
        }
        switch (clickCount) {
            case 0:
                render(tracks.sorted(Comparator.comparing(TrackReadDto::genre)));
                break;
            case 1:
                render(tracks.sorted((a, b) -> b.genre().compareTo(a.genre())));
                break;
            case 2:
                render(tracks);
                break;
        }
        clickCount++;
        clickCount %= 3;
        lastSort = eventBtn;
    }

    @FXML
    public void sortByDuration(Event event) {
        var eventBtn = event.getSource();
        if (lastSort != null && !lastSort.equals(eventBtn)) {
            clickCount = 0;
        }
        switch (clickCount) {
            case 0:
                render(tracks.sorted(Comparator.comparing(TrackReadDto::duration)));
                break;
            case 1:
                render(tracks.sorted((a, b) -> b.duration().compareTo(a.duration())));
                break;
            case 2:
                render(tracks);
                break;
        }
        clickCount++;
        clickCount %= 3;
        lastSort = eventBtn;
    }

    @FXML
    public void sortByExt(Event event) {
        var eventBtn = event.getSource();
        if (lastSort != null && !lastSort.equals(eventBtn)) {
            clickCount = 0;
        }
        switch (clickCount) {
            case 0:
                render(tracks.sorted(Comparator.comparing(TrackReadDto::extension)));
                break;
            case 1:
                render(tracks.sorted((a, b) -> b.extension().compareTo(a.extension())));
                break;
            case 2:
                render(tracks);
                break;
        }
        clickCount++;
        clickCount %= 3;
        lastSort = eventBtn;
    }
}
