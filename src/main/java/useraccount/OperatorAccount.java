package useraccount;

import usertype.Operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorAccount extends BaseUserAccount {
    private final String table_name = "operator_account";

    public OperatorAccount(Operator operator, Connection conn) throws SQLException {
        super(operator, conn);
        super.setSqlTable(table_name);
    }

    public Operator getFullAccount() throws SQLException {
        String sql = " SELECT * FROM " + table_name + " WHERE username = '" + password + "'";
        ResultSet resultSet = stmt.executeQuery(sql);
        if(resultSet.next()){
            return new Operator(super.username, password, resultSet.getBoolean("baseP"), resultSet.getBoolean("stockP"), resultSet.getBoolean("saleP"), resultSet.getBoolean("wmP"));
        }else {
            return null;
        }
    }
}
