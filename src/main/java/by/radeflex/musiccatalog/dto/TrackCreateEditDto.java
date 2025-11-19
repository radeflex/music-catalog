package by.radeflex.musiccatalog.dto;

import by.radeflex.musiccatalog.database.entity.Genre;

import java.io.File;

public record TrackCreateEditDto(
        String title,
        String author,
        Genre genre,
        File file
) {
}