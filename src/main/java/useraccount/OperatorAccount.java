package useraccount;

import usertype.Operator;
import java.sql.Connection;
import java.sql.SQLException;

public class OperatorAccount extends BaserAccount {
    private final String table_name = "user_account";
    public OperatorAccount(Operator operator, Connection conn) throws SQLException {
        super(operator,conn);
        super.setSqlTable (table_name);
    }
}
