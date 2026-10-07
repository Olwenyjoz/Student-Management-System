package studentmanagementsystem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Shared local database configuration for every application screen. */
public final class Database {
    private Database() { }

    public static Connection connect() throws SQLException {
        Path config = Path.of("database.properties");
        Properties settings = new Properties();
        try (InputStream input = Files.newInputStream(config)) {
            settings.load(input);
        } catch (IOException ex) {
            throw new SQLException("Cannot read " + config.toAbsolutePath()
                    + ". Run setup-database.ps1 first.", ex);
        }
        for (String key : new String[] {"db.url", "db.user", "db.password"}) {
            if (!settings.containsKey(key)) {
                throw new SQLException("Missing database setting: " + key);
            }
        }
        return DriverManager.getConnection(settings.getProperty("db.url"),
                settings.getProperty("db.user"), settings.getProperty("db.password"));
    }
}
