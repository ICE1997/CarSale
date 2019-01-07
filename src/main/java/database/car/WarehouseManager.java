package database.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import model.car.Car;
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

    public static ObservableList<Car> getRestCars() throws SQLException {
        ObservableList<Car> cars = FXCollections.observableArrayList ();
        String sql = " SELECT car_manufacturer, model, car_color, car_origin_price  FROM " + tb_name + " WHERE isSold = ?";
        PreparedStatement preparedStatement = conn.prepareStatement (sql);
        preparedStatement.setBoolean (1,false);
        ResultSet resultSet = preparedStatement.executeQuery ();
        while (resultSet.next ()) {
            Car car = new Car (resultSet.getString ("car_manufacturer"),resultSet.getString ("model"),resultSet.getInt ("car_origin_price"),resultSet.getString ("car_color"));
            cars.add (car);
        }
        return cars;
    }

    public static ObservableList<PieChart.Data> getWarehouseCarsChartData() throws SQLException {
        ObservableList<PieChart.Data> warehouseCars = FXCollections.observableArrayList ();
        ArrayList<String> manufacturers = new ArrayList<> ();
        String sql = " SELECT DISTINCT car_manufacturer FROM " + tb_name + " WHERE  isSold = ?";
        PreparedStatement pStmt1 = conn.prepareStatement (sql);
        pStmt1.setBoolean (1,false);
        ResultSet resultSet1 = pStmt1.executeQuery ();
        while (resultSet1.next ()) {
            manufacturers.add (resultSet1.getString ("car_manufacturer"));
        }//获取所有的已出售车的制造商

        ArrayList<String> models = new ArrayList<> ();
        sql = " SELECT DISTINCT model FROM " + tb_name + " WHERE car_manufacturer = ? AND isSold = ?";
        pStmt1 = conn.prepareStatement (sql);
        for(String s : manufacturers) {
            pStmt1.setString (1, s);
            pStmt1.setBoolean (2,false);
            ResultSet resultSet = pStmt1.executeQuery ();
            while (resultSet.next ()) {
                models.add (resultSet.getString ("model"));
            }
        }//获取所有的车型

        sql = " SELECT COUNT(*) AS cars_sold FROM " + tb_name + " WHERE model = ?" ;
        for(String model : models) {
            int count;
            PreparedStatement pStmt = conn.prepareStatement (sql);
            pStmt.setString (1,model);
            ResultSet resultSet = pStmt.executeQuery ();
            while (resultSet.next ()) {
                count = resultSet.getInt ("cars_sold");
                System.out.print(count);
                if(count!=0) {
                    warehouseCars.add (new PieChart.Data (model, count));
                }
            }
        }
        //通过已出售的车型来选择相应的数量
        return warehouseCars;
    }

    public static ObservableList<PieChart.Data> getSoldCarsChartData() throws SQLException {
        ObservableList<PieChart.Data> soldCars = FXCollections.observableArrayList ();
        ArrayList<String> manufacturers = new ArrayList<> ();
        String sql = " SELECT DISTINCT car_manufacturer FROM " + tb_name + " WHERE  isSold = ?";
        PreparedStatement pStmt1 = conn.prepareStatement (sql);
        pStmt1.setBoolean (1,true);
        ResultSet resultSet1 = pStmt1.executeQuery ();
        while (resultSet1.next ()) {
            manufacturers.add (resultSet1.getString ("car_manufacturer"));
        }//获取所有的已出售车的制造商

        ArrayList<String> models = new ArrayList<> ();
        sql = " SELECT DISTINCT model FROM " + tb_name + " WHERE car_manufacturer = ? AND isSold = ?";
        pStmt1 = conn.prepareStatement (sql);
        for(String s : manufacturers) {
            pStmt1.setString (1, s);
            pStmt1.setBoolean (2,true);
            ResultSet resultSet = pStmt1.executeQuery ();
            while (resultSet.next ()) {
                models.add (resultSet.getString ("model"));
            }
        }//获取所有的车型

        sql = " SELECT COUNT(*) AS cars FROM " + tb_name + " WHERE model = ?" ;
            for(String model : models) {
                int count;
                PreparedStatement pStmt = conn.prepareStatement (sql);
                pStmt.setString (1,model);
                ResultSet resultSet = pStmt.executeQuery ();
                while (resultSet.next ()) {
                    count = resultSet.getInt ("cars");
                    System.out.print(count);
                    if(count!=0) {
                        soldCars.add (new PieChart.Data (model, count));
                    }
                }
            }
        //通过已出售的车型来选择相应的数量
        return soldCars;
    }
}
