package useraccount;

import usertype.Admin;

import java.sql.Connection;
import java.sql.SQLException;


public class AdminAccount extends BaseUserAccount {
    private final String table_name = "admin_account";

    public AdminAccount(String workNum, String password, Connection conn) throws SQLException {
        super(workNum, password, conn);
        super.setSqlTable(table_name);
    }

}
