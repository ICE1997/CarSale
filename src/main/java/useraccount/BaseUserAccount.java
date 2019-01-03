package useraccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseUserAccount {
    protected String workNum;
    protected String password;
    Statement stmt;
    private String table_name;

    BaseUserAccount(String workNum, Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.workNum = workNum;
    }

    BaseUserAccount(String workNum, String password, Connection conn) throws SQLException {
        this.workNum = workNum;
        this.password = password;
        stmt = conn.createStatement();
    }

    public boolean login() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + workNum + "'" + " AND password = '" + password + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        return resultSet.next();
    }

    public boolean isExist() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + this.workNum + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        return resultSet.next();
    }

    public String getPassword() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + workNum + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("password");
        } else {
            System.out.println("未找到此用户！");
            return "";
        }
    }

    void setSqlTable(String table_name) {
        this.table_name = table_name;
    }

}
