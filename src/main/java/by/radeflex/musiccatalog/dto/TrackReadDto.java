package by.radeflex.musiccatalog.dto;

import by.radeflex.musiccatalog.database.entity.Extension;
import by.radeflex.musiccatalog.database.entity.Genre;

import java.nio.file.Path;

public record TrackReadDto(
        Integer id,
        String title,
        String author,
        Genre genre,
        String duration,
        Extension extension,
        Path path
) {}
