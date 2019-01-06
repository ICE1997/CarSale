package database.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.StaticDataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseManager {
    private static String tb_name = "cars_info";
    private static Connection conn = StaticDataManager.getConnection ();
    private WarehouseManager() { }

    public static ObservableList<String> getAllManufacturers() throws SQLException {
        ObservableList<String>  observableList = FXCollections.observableArrayList();
        String sql = " SELECT DISTINCT car_manufacturer FROM " + tb_name + " WHERE isSold = ?";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setBoolean (1,false);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("car_manufacturer").trim ());
        }
        return observableList;
    }

    public static ObservableList<String> getModels(String manufacturer) throws SQLException {
        ObservableList<String> observableList = FXCollections.observableArrayList ();
        String sql = " SELECT DISTINCT model FROM " + tb_name + " WHERE car_manufacturer = ? AND  isSold = ?";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1,manufacturer);
        pStmt.setBoolean (2,false);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("model").trim ());
        }
        return observableList;
    }

    public static ObservableList<Integer> getPrice(String manufacturer, String model ) throws SQLException {
        ObservableList<Integer> observableList = FXCollections.observableArrayList ();
        String sql = " SELECT car_origin_price FROM " + tb_name + " WHERE car_manufacturer = ? AND model = ? AND isSold = ?";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1,manufacturer);
        pStmt.setString (2,model);
        pStmt.setBoolean (3,false);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getInt ("car_origin_price"));
        }
        return observableList;
    }

    public static int getId(String manufacturer, String model) throws SQLException {
        ArrayList<Integer> res = new ArrayList<> ();
        String sql = " SELECT id FROM " + tb_name + " WHERE car_manufacturer = ? AND model = ? AND isSold = ?";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1,manufacturer);
        pStmt.setString (2,model);
        pStmt.setBoolean (3,false);
        ResultSet resultSet = pStmt.executeQuery ();
       while (resultSet.next ()) {
           res.add (resultSet.getInt ("id"));
       }
        System.out.println (res.size ()-1);
        return res.get (res.size ()-1);
    }

    public static ObservableList<String> getColors() throws SQLException {
        ObservableList<String>  observableList = FXCollections.observableArrayList();
        String sql = " SELECT DISTINCT car_color FROM " + tb_name + " WHERE isSold = ?" ;
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setBoolean (1,false);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("car_color").trim ());
        }
        return observableList;
    }

    public static int getRestCarsNum() throws SQLException {
        String sql = " SELECT COUNT(*) AS total FROM " + tb_name + " WHERE isSold = ?" ;
        int i = 0;
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setBoolean (1,false);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            i = resultSet.getInt ("total");
        }
        return i;
    }

    public static int getAllCarsNum() throws SQLException {
        String sql = " SELECT COUNT(*) AS allCar FROM " + tb_name ;
        PreparedStatement preparedStatement = conn.prepareStatement (sql);
        ResultSet resultSet = preparedStatement.executeQuery ();
        int count = 0;
        while (resultSet.next ()) count = resultSet.getInt ("allCar");
        return count;
    }
}
