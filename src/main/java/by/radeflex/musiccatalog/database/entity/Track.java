package by.radeflex.musiccatalog.database.entity;

import java.nio.file.Path;
import java.time.Duration;

public class Track {
    private Integer id;
    private String title;
    private String author;
    private Genre genre;
    private final Duration duration;
    private final Path path;
    private final Extension extension;

    public Track(Integer id, String title, String author, Genre genre, Duration duration, Path path, Extension extension) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.duration = duration;
        this.path = path;
        this.extension = extension;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setId(Integer id) { this.id = id; }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Duration getDuration() {
        return duration;
    }

    public Extension getExtension() {
        return extension;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track track)) return false;

        return id.equals(track.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        Integer id;
        String title;
        String author;
        Genre genre;
        Duration duration;
        Extension extension;
        Path path;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder author(String author) {
            this.author = author;
            return this;
        }
        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }
        public Builder duration(Duration duration) {
            this.duration = duration;
            return this;
        }
        public Builder path(Path path) {
            this.path = path;
            return this;
        }

        public Builder extension(Extension extension) {
            this.extension = extension;
            return this;
        }

        public Track build() {
            return new Track(id, title, author, genre, duration, path, extension);
        }
    }

    @Override
    public String toString() {
        return "Track{" + title + ", " + author + ", " + genre + ", " + extension + '}';
    }
}
