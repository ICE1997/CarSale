package useraccount;

import usertype.Admin;

import java.sql.Connection;
import java.sql.SQLException;


public class AdminAccount extends BaseUserAccount {
    private final String table_name = "admin_account";
    public AdminAccount(Admin admin, Connection conn) throws SQLException {
        super(admin,conn);
        super.setSqlTable (table_name);
    }
}
