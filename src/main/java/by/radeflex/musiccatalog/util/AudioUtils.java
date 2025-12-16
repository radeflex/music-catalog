package by.radeflex.musiccatalog.util;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.io.File;
import java.time.Duration;

public class AudioUtils {
    private AudioUtils() {}

    public static Duration getTrackDuration(File file) {
        AudioFile aFile;
        try {
            if (file.getName().split("\\.")[1].equals("aac")) {
                MultimediaObject multimediaObject = new MultimediaObject(file);
                MultimediaInfo info = multimediaObject.getInfo();
                return Duration.ofMillis(info.getDuration());
            }
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
            e.printStackTrace();
            return false;
        }
    }
}
