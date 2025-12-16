package by.radeflex.musiccatalog.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static final String FILE_DIRECTORY = "C:\\Users\\Radeflex\\IdeaProjects\\music-catalog\\tracks\\";

    private FileUtils() {}

    public static Path save(File file) {
        Path path = null;
        if (file != null) {
            path = Paths.get(FILE_DIRECTORY + file.getName());
            if (!Files.exists(path)) {
                try {
                    Files.copy(file.toPath(), path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return path;
    }
    public static void delete(Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
