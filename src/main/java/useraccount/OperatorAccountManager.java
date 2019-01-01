package useraccount;

import usertype.Operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class OperatorAccountManager {
    private String tb_name = "operator_account";
    private Connection conn;
    private Statement stmt;

    OperatorAccountManager(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
    }

    public LinkedList<Operator> getAllOperator(Connection conn) throws SQLException {
        String work_num;
        String name;
        String gender;
        String age;
        String birthday;
        String id_num;
        String phone_num;
        String address;
        LinkedList<Boolean> permissions;
        LinkedList<Operator> operators = null;
        String sql = "SELECT * FROM " + tb_name;
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {

        }
        return operators;
    }
}
