package util;

import usertype.Operator;

import java.sql.Connection;
import java.util.LinkedList;

public class StaticDataManager {
    public static String selected_operator_work_num;
    public static LinkedList<Operator> operators_from_database;
    public static Connection conn;
    public static Operator getOperator() {
        Operator admin_list_selected_operator = null;
        if(operators_from_database!=null) {
            for (Operator operator : operators_from_database) {
                if (operator.getWorkNum ().equals (selected_operator_work_num)) {
                    admin_list_selected_operator = operator;
                }
            }
        }

        return admin_list_selected_operator;
    }
    public static Connection getConnection() {
        return conn;
    }
}
