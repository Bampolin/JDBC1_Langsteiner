package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Driver {
    private String url;
    private String user;
    private String pwd;
    private Connection connection;


    public H2Driver() throws SQLException {
        this("jdbc:h2:~/langiDB", "", "");
    }

    public H2Driver(String url, String user, String pwd) throws SQLException {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.connection = DriverManager.getConnection(
                url,
                user,
                pwd
        );
    }

    public Connection getConnection() {
        return connection;
    }
}