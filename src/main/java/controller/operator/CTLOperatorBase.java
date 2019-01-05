package controller.operator;

import controller.admin.CTLAdminMain;
import database.CarManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.car.Car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLOperatorBase implements Initializable {
    @FXML
    private TableView<Car> operator_base_table;
    private ObservableList<Car> cars_data = FXCollections.observableArrayList();
    private TableColumn<Car,String> carNum = new TableColumn<>("编号");
    private TableColumn<Car,String> carManufacturer = new TableColumn<>("生产商");
    private TableColumn<Car,String> carColor = new TableColumn<>("颜色");
    private TableColumn<Car,String> clientInfo = new TableColumn<>("客户信息");
    private TableColumn<Car,String> clientName = new TableColumn<>("姓名");
    private TableColumn<Car,String> clientAge = new TableColumn<>("年龄");
    private TableColumn<Car,String> clientGender = new TableColumn<>("性别");
    private TableColumn<Car,String> clientPhone = new TableColumn<>("手机号");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTable() {
        carNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        carManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        carColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("c_name"));
        clientAge.setCellValueFactory(new PropertyValueFactory<>("c_age"));
        clientGender.setCellValueFactory(new PropertyValueFactory<>("c_gender"));
        clientPhone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));
        clientInfo.getColumns().addAll(clientName,clientAge,clientGender,clientPhone);
        operator_base_table.getColumns().addAll(carNum,carManufacturer,carColor,clientInfo);
        operator_base_table.setRowFactory(new Row());
    }

    public void loadTable() throws SQLException {
        CarManager carManager = new CarManager();
        cars_data = carManager.getAllCars();
        operator_base_table.setItems(cars_data);
    }


    class Row implements Callback<TableView<Car>, TableRow<Car>> {
        ContextMenu contextMenu;
        TableRow<Car> row;
        @Override
        public TableRow<Car> call(TableView<Car> param) {
            if(contextMenu == null) {
                contextMenu = new RowContextMenu().getInstance();
            }
            row = new TableRow<>();
            row.setOnContextMenuRequested(event -> contextMenu.show(row,event.getScreenX(),event.getScreenY()));
            return row;
        }

        class RowContextMenu extends ContextMenu {
            private ContextMenu INSTANCE = null;

            public RowContextMenu() {
                MenuItem add = new MenuItem("新增");
                MenuItem update = new MenuItem("修改");
                MenuItem delete = new MenuItem("删除");
                update.setOnAction(new Update());
                getItems().addAll(add, update, delete);
            }

            public ContextMenu getInstance() {
                if (INSTANCE == null) {
                    INSTANCE = new RowContextMenu();
                }
                return INSTANCE;
            }

            class Update implements EventHandler<ActionEvent> {
                @Override
                public void handle(ActionEvent event) {
                    operator_base_table.setEditable(true);
//                    carNum.setEditable(true);
//                    row.setEditable(true);
                    operator_base_table.edit(operator_base_table.getSelectionModel().getSelectedIndex(),carNum);
//                    System.out.println(operator_base_table.getSelectionModel().getFocusedIndex()+1);
                    carNum.setCellFactory(TextFieldTableCell.forTableColumn());
                    operator_base_table.edit(operator_base_table.getSelectionModel().getSelectedIndex(),carManufacturer);
                    carManufacturer.setCellFactory(TextFieldTableCell.forTableColumn());

//                    carColor.setCellFactory(TextFieldTableCell.forTableColumn());
//                    clientName.setCellFactory(TextFieldTableCell.forTableColumn());
//                    clientAge.setCellFactory(TextFieldTableCell.forTableColumn());
//                    clientGender.setCellFactory(TextFieldTableCell.forTableColumn());
//                    clientPhone.setCellFactory(TextFieldTableCell.forTableColumn());
                }
            }
        }
    }
}
