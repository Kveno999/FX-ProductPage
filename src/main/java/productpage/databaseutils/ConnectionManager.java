package productpage.databaseutils;

import productpage.generalutils.PropertiesUtils;
import productpage.models.SqlConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionManager {

    private static ConnectionManager instance;
    private Connection connection;

    private ConnectionManager() {
        try {
            SqlConfig sqlConfig = Objects.requireNonNull(PropertiesUtils.readJson("src/main/resources/sqlconfig.json", SqlConfig.class));
            this.connection = DriverManager.getConnection(sqlConfig.getUrl(), sqlConfig.getUsername(), sqlConfig.getPassword());
        } catch (SQLException e) {
            System.out.printf("Exception while getting connection:: %s%n", e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new ConnectionManager();
                }
            } catch (SQLException e) {
                System.out.printf("Exception while getting connection:: %s%n", e.getMessage());
            }
        }
        return instance;
    }

}
