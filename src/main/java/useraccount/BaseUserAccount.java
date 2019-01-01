package useraccount;

import usertype.Admin;
import usertype.Operator;

import java.sql.*;

public class BaseUserAccount {
    private String table_name;
    protected String workNum;
    protected String password;
    protected Statement stmt;

    BaseUserAccount(String workNum, String password, Connection conn) throws SQLException {
        this.workNum = workNum;
        this.password = password;
        stmt = conn.createStatement();
    }

    public boolean login() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + workNum + "'" + " AND password = '" + password + "'";
        ResultSet resultSet = stmt.executeQuery (sql);
        if (resultSet.next ()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + this.workNum + "'";
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
