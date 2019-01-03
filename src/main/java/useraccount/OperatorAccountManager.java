package useraccount;

import model.StaticData;
import usertype.Operator;

import java.sql.*;
import java.util.LinkedList;

public class OperatorAccountManager {
    private String tb_name = "operator_account";
    private Connection conn;
    private Statement stmt;

    public OperatorAccountManager(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
    }

    public LinkedList<Operator> getAllOperator() throws SQLException {
        String work_num;
        String name;
        Boolean gender;
        int age;
        String birthday;
        String id_num;
        String phone_num;
        String address;
        LinkedList<Boolean> permissions = new LinkedList<>();
        LinkedList<Operator> operators = new LinkedList<>();
        String sql = "SELECT * FROM " + tb_name;
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            work_num = resultSet.getString("username");
            name = resultSet.getString("name");
            gender = resultSet.getBoolean("gender");
            age = resultSet.getInt("age");
            birthday = resultSet.getString("birthday");
            id_num = resultSet.getString("id_num");
            phone_num = resultSet.getString("phone_num");
            address = resultSet.getString("address");
            permissions.add(resultSet.getBoolean("baseP"));
            permissions.add(resultSet.getBoolean("stockP"));
            permissions.add(resultSet.getBoolean("saleP"));
            permissions.add(resultSet.getBoolean("wmP"));
            operators.add(new Operator(work_num,name,gender,age,birthday,id_num,phone_num,address,permissions));
        }
        StaticData.operators_from_database = operators;
        return operators;
    }

    public void addOperator(Operator operator) throws SQLException {
        LinkedList<Boolean> linkedList = new LinkedList<>();
        linkedList.add(true);
        linkedList.add(true);
        linkedList.add(true);
        linkedList.add(true);
        String password = "141";
        Operator operator1 = new Operator("2017211600","何某某",true,11,"1998-08-09","123123","12313131","12312313", linkedList);
        String sql = " INSERT INTO " + tb_name + " ( username, password, baseP, stockP, saleP, wmP, name, gender, age, birthday, id_num, phone_num, address ) " +
                " VALUES ( " +
                "'"+operator1.getWorkNum()+"'," +
                "'"+password+"'," +
                "'"+convertBoolean(operator1.getPermissions().get(0))+"',"  +
                "'"+convertBoolean(operator1.getPermissions().get(1))+"',"  +
                "'"+convertBoolean(operator1.getPermissions().get(2))+"',"  +
                "'"+convertBoolean(operator1.getPermissions().get(3))+"',"  +
                "'"+operator1.getName()+"',"  +
                "'"+convertBoolean(operator1.isGirl())+"',"  +
                "'"+operator1.getAge()+"',"  +
                "'"+operator1.getBirthday()+"',"  +
                "'"+operator1.getId_num()+"',"  +
                "'"+operator1.getPhone_num()+"',"  +
                "'"+operator1.getAddress()+"'"  +
                ") ";
        stmt.execute(sql);
    }

    public void updateOperator(Operator operator) throws SQLException {
        LinkedList<Boolean> linkedList = new LinkedList<>();
        linkedList.add(true);
        linkedList.add(false);
        linkedList.add(true);
        linkedList.add(false);
        Operator operator1 = new Operator("2017211600","何发",true,11,"1998-08-09","123123","12313131","12312313", linkedList);
        String password = "141";
        String sql = " UPDATE " +  tb_name + " SET " +
                " password = '" + password +"'," +
                " baseP = '" + convertBoolean(operator1.getPermissions().get(0)) +"'," +
                " stockP = '" + convertBoolean(operator1.getPermissions().get(1)) +"'," +
                " saleP = '" + convertBoolean(operator1.getPermissions().get(2)) +"'," +
                " wmP = '" + convertBoolean(operator1.getPermissions().get(3)) +"'," +
                " name = '" + operator1.getName() +"'," +
                " gender = '" + convertBoolean(operator1.isGirl ()) +"'," +
                " age = '" + operator1.getAge() +"'," +
                " birthday = '" + operator1.getBirthday() +"'," +
                " id_num = '" + operator1.getId_num() +"'," +
                " phone_num = '" + operator1.getPhone_num() +"'," +
                " address = '" + operator1.getAddress() +"'" +
                " WHERE username = '" + operator1.getWorkNum() + "'";

        stmt.execute(sql);

    }
    public void removeOperator(String workNum) throws SQLException {
        String sql = " DELETE FROM " + tb_name + " WHERE username = '" + workNum + "'";
        stmt.execute(sql);
    }

    public boolean isExist(String workNum) throws SQLException {
        String sql = " SELECT * FROM " + tb_name + " WHERE username = '" + workNum + "'";
        ResultSet resultSet = stmt.executeQuery (sql);
        if (resultSet.next ()) {
            return true;
        } else {
            return false;
        }
    }

    int convertBoolean(Boolean b) {
        return b ? 1 : 0;
    }
}
