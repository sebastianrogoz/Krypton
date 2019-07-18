package DAL;

import java.sql.*;

public class DatabaseConnectionOld {
    private String url;
    private String user;
    private String password;

    private static final DatabaseConnectionOld instance =
            new DatabaseConnectionOld(
                    "jdbc:postgresql://localhost:5432/KRYPTON",
                    "postgres",
                    "sebastian1#"
            );

    private DatabaseConnectionOld(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConnectionOld getInstance() {
        return instance;
    }

    public void executeSql(String sql) {
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            this.disconnect(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;


        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to postgres");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private void disconnect(Connection conn) {
        try{
            conn.close();
            System.out.println("Disconnected from postgres");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
