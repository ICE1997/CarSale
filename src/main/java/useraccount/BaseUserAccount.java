package useraccount;

import usertype.BaseUserType;
import usertype.Operator;

import java.sql.*;

public class BaseUserAccount {
    private String table_name;
    protected String username;
    protected String password;
    protected Statement stmt;

    public BaseUserAccount(BaseUserType baseUsetType, Connection conn) throws SQLException {
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
