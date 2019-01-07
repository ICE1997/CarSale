package controller.operator;

import database.car.WarehouseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.car.Car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLOperatorWm implements Initializable {
    @FXML
    private PieChart warehouse_chart;
    @FXML
    private PieChart sold_chart;
    @FXML
    private TableView warehouseCarsTable;
    @FXML
    SplitPane s;

    ObservableList<PieChart.Data> warehouse_cars_data = FXCollections.observableArrayList ();
    ObservableList<PieChart.Data> sold_cars_data = FXCollections.observableArrayList ();

    ObservableList<Car> rest_cars_info = FXCollections.observableArrayList ();
    TableColumn<Car,String> car_manufacturer = new TableColumn<> ("生产商");
    TableColumn<Car,String> car_model = new TableColumn<> ("型号");
    TableColumn<Car,String> car_color = new TableColumn<> ("颜色");
    TableColumn<Car,String> car_price = new TableColumn<> ("进价");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initChart ();
            initTable ();
            s.setOnContextMenuRequested (new EventHandler<ContextMenuEvent> () {
                ContextMenu contextMenu = null;
                @Override
                public void handle(ContextMenuEvent event) {
                    if(contextMenu == null) {
                        contextMenu = new WmContextMenu ().getInstance ();
                    }
                    contextMenu.show (s,event.getScreenX (),event.getScreenY ());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    void initChart() throws SQLException {
        warehouse_cars_data = WarehouseManager.getWarehouseCarsChartData ();
        sold_cars_data = WarehouseManager.getSoldCarsChartData ();

        if(warehouse_cars_data!=null) {
            warehouse_chart.setTitle ("库存车辆");
            warehouse_chart.setData (warehouse_cars_data);
            warehouse_chart.setLegendSide (Side.RIGHT);
        }

        if(sold_cars_data != null) {
            sold_chart.setTitle ("已售车辆");
            sold_chart.setData (sold_cars_data);
            sold_chart.setLegendSide (Side.RIGHT);
        }
    }

    void  initTable() throws SQLException {

        rest_cars_info = WarehouseManager.getRestCars ();
        warehouseCarsTable.setItems (rest_cars_info);
        car_manufacturer.setCellValueFactory (new PropertyValueFactory<> ("manufacturer"));
        car_model.setCellValueFactory (new PropertyValueFactory<> ("model"));
        car_color.setCellValueFactory (new PropertyValueFactory<> ("color"));
        car_price.setCellValueFactory (new PropertyValueFactory<> ("origin_price"));
        warehouseCarsTable.getColumns ().addAll (car_manufacturer,car_model,car_color,car_price);

    }

    class WmContextMenu extends ContextMenu {
        private ContextMenu INSTANCE = null;

        public WmContextMenu() {
            MenuItem refresh = new MenuItem ("刷新");
            refresh.setOnAction (event -> {
                try {
                    rest_cars_info = WarehouseManager.getRestCars ();
                    warehouseCarsTable.setItems (rest_cars_info);

                    warehouse_cars_data = WarehouseManager.getWarehouseCarsChartData ();
                    warehouse_chart.setData (warehouse_cars_data);

                    sold_cars_data = WarehouseManager.getSoldCarsChartData ();
                    sold_chart.setData (sold_cars_data);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            });
            getItems ().add(refresh);
        }
        public ContextMenu getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new WmContextMenu ();
            }
            return INSTANCE;
        }
    }
}
