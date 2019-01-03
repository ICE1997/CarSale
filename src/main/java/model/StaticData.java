package model;

import usertype.Operator;

import java.util.LinkedList;

public class StaticData {
    public static Operator admin_list_selected_operator;
    public static String selected_operator_work_num;
    public static LinkedList<Operator> operators_from_database;

    public static Operator getOperator() {
        for (Operator operator: operators_from_database) {
            if (operator.getWorkNum ().equals (selected_operator_work_num)) {
                admin_list_selected_operator = operator;
            }
        }
        return admin_list_selected_operator;
    }

}
