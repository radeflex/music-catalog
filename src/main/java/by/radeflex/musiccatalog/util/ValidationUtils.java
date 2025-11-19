package by.radeflex.musiccatalog.util;

import by.radeflex.musiccatalog.dto.TrackCreateEditDto;
import by.radeflex.musiccatalog.exception.ValidationException;
import by.radeflex.musiccatalog.lib.MyMap;

public class ValidationUtils {
    private ValidationUtils() {}

    public static void validate(TrackCreateEditDto dto) throws ValidationException {
        MyMap<String, String> map = new MyMap<>();
        if (dto.title().isBlank()) {
            map.put("title", "не может быть пустым");
        }
        if (dto.title().length() > 30) {
            map.put("title", "максимум 30 символов");
        }
        if (dto.author().isBlank()) {
            map.put("author", "не может быть пустым");
        }
        if (dto.author().length() > 30) {
            map.put("author", "максимум 30 символов");
        }
        if (dto.file() == null) {
            map.put("file", "выберите аудиофайл");
        } else if (!AudioUtils.isAudioFile(dto.file())) {
            map.put("file", "некорректный аудиофайл");
        }

        if (!map.isEmpty())
            throw new ValidationException(map);
    }
}
