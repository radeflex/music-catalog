package by.radeflex.musiccatalog.database.jdbc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private final String URL = "jdbc:sqlite:/home/radeflex/JBProjects/IdeaProjects/music-catalog/src/main/resources/by/radeflex/musiccatalog/storage.db";

    private ConnectionPool() {
        final String SCHEMA_PATH = "/by/radeflex/musiccatalog/schema.sql";
        try (var stmt = DriverManager.getConnection(URL).createStatement()) {
            var schemaURI = ConnectionPool.class.getResource(SCHEMA_PATH).toURI();
            stmt.execute(Files.readString(Path.of(schemaURI), StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
