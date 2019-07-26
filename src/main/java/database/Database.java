package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql:///accounts?useUnicode=yes&characterEncoding=utf8";
    private final static String USER = "";//
    private final static String PSW = "";//
    private static Connection conn;
    private Database() {
    }
    public static Connection getConnection() {
        try {
            Class.forName (MYSQL_DRIVER);
            System.out.println ("Connect to the database......");
            conn = DriverManager.getConnection (DB_URL, USER, PSW);
            System.out.println ("Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
        }
        return conn;
    }
}



