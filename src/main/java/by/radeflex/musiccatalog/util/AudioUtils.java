package by.radeflex.musiccatalog.util;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.time.Duration;

public class AudioUtils {
    private AudioUtils() {}

    public static Duration getTrackDuration(File file) {
        AudioFile aFile;
        try {
            aFile = AudioFileIO.read(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Duration.ofSeconds(aFile.getAudioHeader().getTrackLength());
    }

    public static Boolean isAudioFile(File file) {
        try {
            AudioFileIO.read(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
