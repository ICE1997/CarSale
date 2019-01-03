package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://47.106.132.194:3306/accounts?useUnicode=yes&characterEncoding=utf8";
    private final static String USER = "root";
    private final static String PSW = "ice@1997";
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



