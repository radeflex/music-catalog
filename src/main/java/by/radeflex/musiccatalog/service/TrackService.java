package by.radeflex.musiccatalog.service;

import by.radeflex.musiccatalog.dto.TrackCreateEditDto;
import by.radeflex.musiccatalog.dto.TrackReadDto;
import by.radeflex.musiccatalog.database.entity.Extension;
import by.radeflex.musiccatalog.exception.ValidationException;
import by.radeflex.musiccatalog.lib.MyLinkedList;
import by.radeflex.musiccatalog.util.TrackMapper;
import by.radeflex.musiccatalog.database.repository.TrackRepository;

import static by.radeflex.musiccatalog.util.ValidationUtils.validate;

public class TrackService {
    private static final TrackService INSTANCE = new TrackService();
    private final TrackRepository trackRepository = TrackRepository.getInstance();

    private TrackService() {}

    public static TrackService getInstance() {
        return INSTANCE;
    }

    private static String extractExt(TrackCreateEditDto trackCreateEditDto) {
        return trackCreateEditDto.file().getName()
                .substring(trackCreateEditDto.file().getName().lastIndexOf(".") + 1).toUpperCase();
    }

    public TrackReadDto save(TrackCreateEditDto trackCreateEditDto) throws ValidationException {
        validate(trackCreateEditDto);
        var ext = extractExt(trackCreateEditDto);
        var track = TrackMapper.mapFrom(trackCreateEditDto, Extension.valueOf(ext));
        return TrackMapper.mapFrom(trackRepository.save(track));
    }

    public TrackReadDto update(Integer id, TrackCreateEditDto trackCreateEditDto) {
        validate(trackCreateEditDto);
        var ext = extractExt(trackCreateEditDto);
        var track = TrackMapper.mapFrom(trackCreateEditDto, Extension.valueOf(ext));
        track.setId(id);
        trackRepository.update(id, track);
        return TrackMapper.mapFrom(track);
    }

    public MyLinkedList<TrackReadDto> findAll() {
        var tracks = trackRepository.findAll();
        var trackReadDtos = new MyLinkedList<TrackReadDto>();

        for (var track : tracks) {
            var mapped = TrackMapper.mapFrom(track);
            trackReadDtos.add(mapped);
        }

        return trackReadDtos;
    }

    public void delete(Integer id) {
        trackRepository.delete(id);
    }
}
