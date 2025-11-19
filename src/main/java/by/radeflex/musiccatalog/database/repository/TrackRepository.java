package by.radeflex.musiccatalog.database.repository;

import by.radeflex.musiccatalog.database.entity.Extension;
import by.radeflex.musiccatalog.database.jdbc.ConnectionPool;
import by.radeflex.musiccatalog.database.entity.Genre;
import by.radeflex.musiccatalog.database.entity.Track;
import by.radeflex.musiccatalog.lib.MyLinkedList;

import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

public class TrackRepository {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final TrackRepository INSTANCE =  new TrackRepository();
    private static final String FIND_ALL = "SELECT * FROM track";
    private static final String SAVE = "INSERT INTO track(title, author, genre, duration, extension, path) VALUES(?, ?, ?, ?, ?, ?) RETURNING id";
    private static final String UPDATE = "UPDATE track SET title=?, author=?, genre=? WHERE id = ?";
    private static final String DELETE = "DELETE FROM track WHERE id = ?";

    private TrackRepository() {}

    public static TrackRepository getInstance() {
        return INSTANCE;
    }

    public MyLinkedList<Track> findAll() {
        try (Statement stmt = connectionPool.getConnection().createStatement()) {
            MyLinkedList<Track> tracks = new MyLinkedList<>();
            var resultSet = stmt.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                var track = Track.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .genre(Genre.valueOf(resultSet.getString("genre")))
                        .duration(Duration.ofSeconds(resultSet.getInt("duration")))
                        .extension(Extension.valueOf(resultSet.getString("extension")))
                        .path(Path.of(resultSet.getString("path")))
                        .build();
                tracks.add(track);
            }
            return tracks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Track save(Track track) {
        try (PreparedStatement stmt = connectionPool.getConnection().prepareStatement(SAVE)) {
            stmt.setString(1, track.getTitle());
            stmt.setString(2, track.getAuthor());
            stmt.setString(3, track.getGenre().toString());
            stmt.setLong(4, track.getDuration().getSeconds());
            stmt.setString(5, track.getExtension().toString());
            stmt.setString(6, track.getPath().toString());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                track.setId(rs.getInt("id"));
            }
            return track;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Integer id, Track track) {
        try (PreparedStatement stmt = connectionPool.getConnection().prepareStatement(UPDATE)) {
            stmt.setString(1, track.getTitle());
            stmt.setString(2, track.getAuthor());
            stmt.setString(3, track.getGenre().toString());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stmt = connectionPool.getConnection().prepareStatement(DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
