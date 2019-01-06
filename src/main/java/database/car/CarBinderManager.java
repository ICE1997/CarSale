package database.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.car.CarBinder;
import util.StaticDataManager;

import java.sql.*;

public class CarBinderManager {
    private String tb_name = "cars_info";
    private Connection conn;

    public CarBinderManager() {
        conn = StaticDataManager.getConnection();
    }

    public  ObservableList<CarBinder> getAllCarsInfo() throws SQLException {
        ObservableList<CarBinder>  observableList = FXCollections.observableArrayList();
        String sql = " SELECT  * FROM " + tb_name + " WHERE isSold = ?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setBoolean (1,true);
        ResultSet res = pStmt.executeQuery();
        while (res.next()) {
            observableList.add(new CarBinder (res.getString("id"),res.getString("car_manufacturer"),res.getString("car_color"),res.getString ("model"),res.getInt ("car_sale_price"),res.getBoolean ("isSold"),res.getString("car_client_name"),res.getInt("car_client_age"),res.getString("car_client_gender"),res.getString("car_client_phone_num")));
        }
        return observableList;
    }

    public void updateCarInfo(CarBinder carBinder) throws SQLException {
        String sql = " UPDATE " + tb_name + " SET " +
                " isSold = ? ," +
                " car_client_name = ?, "+
                " car_client_age = ?, "+
                " car_client_gender = ?, "+
                " car_client_phone_num = ?, " +
                " car_sale_price = ? " +
                " WHERE id = ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setBoolean (1,carBinder.isIsSold ());
        pStmt.setString(2, carBinder.getC_name());
        pStmt.setInt(3, Integer.parseInt (carBinder.getC_age()));
        pStmt.setString(4, carBinder.getC_gender());
        pStmt.setString(5, carBinder.getC_phone());
        pStmt.setInt (6,carBinder.getSale_price ());
        pStmt.setString(7, carBinder.getNum());
        pStmt.executeUpdate();
    }

    public void  addCarInfo(CarBinder carBinder) throws SQLException {
        String sql = " UPDATE " + tb_name +
                " SET isSold =?, " +
                "car_client_name = ?, " +
                "car_client_age = ?, " +
                "car_client_gender = ?, " +
                "car_client_phone_num = ?, " +
                "car_sale_price = ? WHERE id= ?";
        PreparedStatement pStmt = conn.prepareStatement (sql);
        pStmt.setBoolean (1,carBinder.isIsSold ());
        pStmt.setString (2,carBinder.getC_name ());
        pStmt.setInt (3,Integer.parseInt (carBinder.getC_age ()));
        pStmt.setString (4,carBinder.getC_gender ());
        pStmt.setString (5,carBinder.getC_phone ());
        pStmt.setInt (6,carBinder.getSale_price ());
        pStmt.setString (7,carBinder.getNum ());
        pStmt.execute ();
    }
}
