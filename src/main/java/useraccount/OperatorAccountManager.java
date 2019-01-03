package useraccount;

import usertype.Operator;
import util.StaticDataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import static util.Converter.BooleanToInt;

public class OperatorAccountManager {
    private String tb_name;
    private Connection conn;
    private Statement stmt;

    public OperatorAccountManager() throws SQLException {
        stmt = StaticDataManager.getConnection().createStatement();
        tb_name = "operator_account";
    }

    public LinkedList<Operator> getAllOperator() throws SQLException {
        String work_num;
        String name;
        Boolean gender;
        String age;
        String birthday;
        String id_num;
        String phone_num;
        String address;
        Boolean baseP;
        Boolean stockP;
        Boolean saleP;
        Boolean wmP;

        LinkedList<Operator> operators = new LinkedList<>();
        String sql = "SELECT * FROM " + tb_name;
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            work_num = resultSet.getString("username");
            name = resultSet.getString("name");
            gender = resultSet.getBoolean("gender");
            age = resultSet.getString("age");
            birthday = resultSet.getString("birthday");
            id_num = resultSet.getString("id_num");
            phone_num = resultSet.getString("phone_num");
            address = resultSet.getString("address");
            baseP = resultSet.getBoolean("baseP");
            stockP = resultSet.getBoolean("stockP");
            saleP = resultSet.getBoolean("saleP");
            wmP = resultSet.getBoolean("wmP");
            operators.add(new Operator(work_num, name, gender, age, birthday, id_num, phone_num, address, baseP, stockP, saleP, wmP));
        }
        StaticDataManager.operators_from_database = operators;
        return operators;
    }

    public void addOperator(Operator operator, String password) throws SQLException {
        String sql = " INSERT INTO " + tb_name + " ( username, password, baseP, stockP, saleP, wmP, name, gender, age, birthday, id_num, phone_num, address ) " +
                " VALUES ( " +
                "'" + operator.getWorkNum() + "'," +
                "'" + password + "'," +
                "'" + BooleanToInt(operator.getBaseP()) + "'," +
                "'" + BooleanToInt(operator.getStockP()) + "'," +
                "'" + BooleanToInt(operator.getSaleP()) + "'," +
                "'" + BooleanToInt(operator.getWmP()) + "'," +
                "'" + operator.getName() + "'," +
                "'" + BooleanToInt(operator.isGirl()) + "'," +
                "'" + operator.getAge() + "'," +
                "'" + operator.getBirthday() + "'," +
                "'" + operator.getId_num() + "'," +
                "'" + operator.getPhone_num() + "'," +
                "'" + operator.getAddress() + "'" +
                ") ";
        stmt.execute(sql);
    }

    public void updateOperator(Operator operator, String password) throws SQLException {
        LinkedList<Boolean> linkedList = new LinkedList<>();
        linkedList.add(true);
        linkedList.add(false);
        linkedList.add(true);
        linkedList.add(false);
        String sql = " UPDATE " + tb_name + " SET " +
                " password = '" + password + "'," +
                " baseP = '" + BooleanToInt(operator.getBaseP()) + "'," +
                " stockP = '" + BooleanToInt(operator.getStockP()) + "'," +
                " saleP = '" + BooleanToInt(operator.getSaleP()) + "'," +
                " wmP = '" + BooleanToInt(operator.getWmP()) + "'," +
                " name = '" + operator.getName() + "'," +
                " gender = '" + BooleanToInt(operator.isGirl()) + "'," +
                " age = '" + operator.getAge() + "'," +
                " birthday = '" + operator.getBirthday() + "'," +
                " id_num = '" + operator.getId_num() + "'," +
                " phone_num = '" + operator.getPhone_num() + "'," +
                " address = '" + operator.getAddress() + "'" +
                " WHERE username = '" + operator.getWorkNum() + "'";

        stmt.execute(sql);

    }

    public void removeOperator(String workNum) throws SQLException {
        String sql = " DELETE FROM " + tb_name + " WHERE username = '" + workNum + "'";
        stmt.execute(sql);
    }

    public boolean isExist(String workNum) throws SQLException {
        String sql = " SELECT * FROM " + tb_name + " WHERE username = '" + workNum + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        return resultSet.next();
    }


}
