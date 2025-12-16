package by.radeflex.musiccatalog.util;

import by.radeflex.musiccatalog.dto.TrackCreateEditDto;
import by.radeflex.musiccatalog.database.entity.Extension;
import by.radeflex.musiccatalog.database.entity.Track;
import by.radeflex.musiccatalog.dto.TrackReadDto;

import java.nio.file.Path;

public class TrackMapper {
    private TrackMapper() {}

    public static Track mapFrom(TrackCreateEditDto trackCreateEditDto, Extension extension, Path path) {
        var file = trackCreateEditDto.file();
        return Track.builder()
                .title(trackCreateEditDto.title())
                .author(trackCreateEditDto.author())
                .genre(trackCreateEditDto.genre())
                .path(path)
                .duration(AudioUtils.getTrackDuration(file))
                .extension(extension)
                .build();
    }

    public static TrackReadDto mapFrom(Track track) {
        var dur = track.getDuration();
        var stringDuration = String.format("%d:%d", dur.toMinutes(), dur.toSeconds() - 60 * dur.toMinutes());
        return new TrackReadDto(
                track.getId(),
                track.getTitle(),
                track.getAuthor(),
                track.getGenre(),
                stringDuration,
                track.getExtension(),
                track.getPath());
    }
}
