package database.useraccount;

import model.usertype.Operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorAccount extends BaseUserAccount {
    private final String table_name = "operator_account";


    public OperatorAccount(String password, Connection conn) throws SQLException {
        super(password, conn);
        super.setSqlTable(table_name);
    }

    public OperatorAccount(String workNum, String password, Connection conn) throws SQLException {
        super(workNum, password, conn);
        super.setSqlTable(table_name);
    }

    public Operator getFullAccount() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + workNum + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        if (resultSet.next()) {
            return new Operator(super.workNum, resultSet.getBoolean("baseP"), resultSet.getBoolean("stockP"), resultSet.getBoolean("saleP"), resultSet.getBoolean("wmP"));
        } else {
            return null;
        }
    }
}
