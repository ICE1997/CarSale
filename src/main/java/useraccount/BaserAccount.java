package useraccount;

import usertype.BaseUsetType;
import usertype.Operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaserAccount {
    private String table_name;
    private String username;
    private String password;
    private Statement stmt;

    public BaserAccount(BaseUsetType baseUsetType, Connection conn) throws SQLException {
        this.username = baseUsetType.getName ();
        this.password = baseUsetType.getPassword ();
        stmt = conn.createStatement ();
    }

    public boolean login() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + username + "'" + " AND password = '" + password + "'";
        ResultSet resultSet = stmt.executeQuery (sql);
        if (resultSet.next ()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + this.username + "'";
        ResultSet resultSet = stmt.executeQuery (sql);
        if (resultSet.next ()) {
            return true;
        } else {
            return false;
        }
    }

    void setSqlTable(String table_name){
        this.table_name = table_name;
    }

}
