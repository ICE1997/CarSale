package database.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.car.Car;
import util.StaticDataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class CarManager {

    private static String tb_name = "market_cars";
    private static Connection conn = StaticDataManager.getConnection ();
    private static String tb_color = "market_cars_color";
    private static String tb_base = "cars_info";

    public static ObservableList<String> getAllManufacturers() throws SQLException {
        ObservableList<String>  observableList = FXCollections.observableArrayList();
        String sql = " SELECT DISTINCT name FROM " + tb_name ;
        PreparedStatement pStmt = conn.prepareStatement (sql);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("name").trim ());
        }
        return observableList;
    }

    public static ObservableList<String> getModels(String manufacturer) throws SQLException {
        ObservableList<String> observableList = FXCollections.observableArrayList ();
        String sql = " SELECT DISTINCT model FROM " + tb_name + " WHERE name = ? ";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1,manufacturer);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("model"));
        }
        return observableList;
    }

    public static ObservableList<Integer> getPrice(String manufacturer, String model ) throws SQLException {
        ObservableList<Integer> observableList = FXCollections.observableArrayList ();
        String sql = " SELECT origin_price FROM " + tb_name + " WHERE name = ? AND model = ? ";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1,manufacturer);
        pStmt.setString (2,model);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getInt ("origin_price"));
        }
        return observableList;
    }

    public static ObservableList<String> getColors() throws SQLException {

        ObservableList<String>  observableList = FXCollections.observableArrayList();
        String sql = " SELECT DISTINCT color FROM " + tb_color ;
        PreparedStatement pStmt = conn.prepareStatement (sql);
        ResultSet resultSet = pStmt.executeQuery ();
        while (resultSet.next ()) {
            observableList.add (resultSet.getString ("color").trim ());
        }
        return observableList;
    }

    public static void addCar(Car car) throws SQLException {
        String sql = " INSERT INTO " + tb_base + " (car_manufacturer, car_color, model, car_origin_price) VALUES(?,?,?,?) ";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setString (1, car.getManufacturer ());
        pStmt.setString (2, car.getColor ());
        pStmt.setString (3,car.getModel ());
        pStmt.setInt (4,car.getOrigin_price ());
        pStmt.execute ();
    }
}
