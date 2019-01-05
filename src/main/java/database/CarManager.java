package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.car.Car;
import util.StaticDataManager;

import java.sql.*;

public class CarManager {
    private String tb_name = "cars_info";
    private Connection conn;

    public CarManager() {
        conn = StaticDataManager.getConnection();
    }

    public  ObservableList<Car> getAllCars() throws SQLException {
        ObservableList<Car>  observableList = FXCollections.observableArrayList();

        String sql = " SELECT  * FROM " + tb_name;
        PreparedStatement pStmt = conn.prepareStatement(sql);
        ResultSet res = pStmt.executeQuery();

        while (res.next()) {
            observableList.add(new Car(res.getString("car_num"),res.getString("car_manufacturer"),res.getString("car_color"),res.getString("car_client_name"),res.getInt("car_client_age"),res.getString("car_client_gender"),res.getString("car_client_phone_num")));
        }

        return observableList;

    }

    public void addCar(Car car) throws SQLException {
        String sql = " INSERT INTO " + tb_name + " ( car_num, car_manufacturer, car_color, car_client_name, car_client_age, car_client_gender, car_client_phone_num) VALUES (?,?,?,?,?,?,?) " ;
        PreparedStatement pStmt = conn.prepareStatement(sql);

        pStmt.setString(1,car.getNum());
        pStmt.setString(2,car.getManufacturer());
        pStmt.setString(3,car.getColor());
        pStmt.setString(4,car.getC_name());
        pStmt.setInt(5,car.getC_age());
        pStmt.setString(6,car.getC_gender());
        pStmt.setString(7,car.getC_phone());

        pStmt.execute();
    }

    public void deleteCar(String num) throws SQLException {
        String sql = " DELETE FROM " + tb_name + " WHERE car_num =  ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1,num);
        pStmt.execute();
    }

    public void updateCar(Car car) throws SQLException {
        String sql = " UPDATE " + tb_name + " SET " +
                " car_manufacturer = ?, "+
                " car_color = ?, "+
                " car_client_name = ?, "+
                " car_client_age = ?, "+
                " car_client_gender = ?, "+
                " car_client_phone_num = ? " + " WHERE car_num = ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);

        pStmt.setString(1,car.getManufacturer());
        pStmt.setString(2,car.getColor());
        pStmt.setString(3,car.getC_name());
        pStmt.setInt(4,car.getC_age());
        pStmt.setString(5,car.getC_gender());
        pStmt.setString(6,car.getC_phone());
        pStmt.setString(7,car.getNum());

        pStmt.executeUpdate();
    }

    public boolean isExist(String num) throws SQLException {
        String sql = " SELECT * FROM " + tb_name + " WHERE car_num = ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);

        pStmt.setString(1,num);

        return pStmt.executeQuery().next();
    }

}
