package controller.operator;

import database.car.CarBinderManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import model.car.CarBinder;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLOperatorBase implements Initializable {
    @FXML
    private TableView<CarBinder> operator_base_table;
    private ObservableList<CarBinder> cars_data = FXCollections.observableArrayList();
    private TableColumn<CarBinder,String> carNum = new TableColumn<>("编号");
    private TableColumn<CarBinder,String> carManufacturer = new TableColumn<>("生产商");
    private TableColumn<CarBinder,String> carModel = new TableColumn<>("型号");
    private TableColumn<CarBinder,String> carPrice = new TableColumn<>("成交价");
    private TableColumn<CarBinder,String> carColor = new TableColumn<>("颜色");
    private TableColumn<CarBinder,String> clientInfo = new TableColumn<>("客户信息");
    private TableColumn<CarBinder,String> clientName = new TableColumn<>("姓名");
    private TableColumn<CarBinder,String> clientAge = new TableColumn<>("年龄");
    private TableColumn<CarBinder,String> clientGender = new TableColumn<>("性别");
    private TableColumn<CarBinder,String> clientPhone = new TableColumn<>("手机号");
    CarBinderManager carBinderManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carBinderManager = new CarBinderManager ();
        new Thread (() -> {
            initTable();
            try {
                loadTable();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }).start ();
    }

    public void initTable() {
        carNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        carManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        carColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        carModel.setCellValueFactory (new PropertyValueFactory<> ("model"));
        carPrice.setCellValueFactory (new PropertyValueFactory<> ("sale_price"));


        clientName.setCellValueFactory(new PropertyValueFactory<>("c_name"));
        clientName.setOnEditCancel (event -> operator_base_table.setEditable (false));
        clientName.setOnEditCommit (event -> {
            CarBinder carBinder = event.getRowValue ();
            carBinder.setC_name (event.getNewValue ());
            try {
                if("".equals (event.getNewValue ())){
                    operator_base_table.getItems ().remove (event.getTablePosition ().getRow ());
                    carBinder.setIsSold (false);
                    carBinder.setSale_price (0);
                    carBinder.setC_name ("");
                    carBinder.setC_gender ("");
                    carBinder.setC_phone ("");
                    carBinder.setC_age (0);
                }else {
                    carBinder.setIsSold (true);
                }
                operator_base_table.setEditable (false);
                carBinderManager.updateCarInfo (carBinder);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });

        clientAge.setCellValueFactory(new PropertyValueFactory<>("c_age"));
        clientAge.setOnEditCancel (event -> operator_base_table.setEditable (false));
        clientAge.setOnEditCommit (event -> {
            CarBinder carBinder = event.getRowValue ();
            carBinder.setC_age (Integer.parseInt (event.getNewValue ()));
            try {
                operator_base_table.setEditable (false);
                carBinderManager.updateCarInfo (carBinder);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });

        clientGender.setCellValueFactory(new PropertyValueFactory<>("c_gender"));
        clientGender.setOnEditCommit (event -> {
            CarBinder carBinder = event.getRowValue ();
            carBinder.setC_gender (event.getNewValue ());
            try {
                operator_base_table.setEditable (false);
                carBinderManager.updateCarInfo (carBinder);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });
        clientGender.setOnEditCancel (event -> operator_base_table.setEditable (false));

        clientPhone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));
        clientPhone.setOnEditCancel (event -> operator_base_table.setEditable (false));
        clientPhone.setOnEditCommit (event -> {
            CarBinder carBinder = event.getRowValue ();
            carBinder.setC_phone (event.getNewValue ());
            try {
                operator_base_table.setEditable (false);
                carBinderManager.updateCarInfo (carBinder);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });


        clientInfo.getColumns().addAll(clientName,clientAge,clientGender,clientPhone);
        operator_base_table.getColumns().addAll(carNum,carManufacturer,carColor,carModel,carPrice,clientInfo);
//        operator_base_table.setRowFactory(new Row());
        operator_base_table.setOnContextMenuRequested (new EventHandler<ContextMenuEvent> () {
            ContextMenu contextMenu = null;
            @Override
            public void handle(ContextMenuEvent event) {
                if(contextMenu == null) {
                    contextMenu = new TableContextMenu ().getInstance ();
                }
                contextMenu.show (operator_base_table,event.getScreenX (),event.getScreenY ());
            }
        });
    }

    public void loadTable() throws SQLException {
        cars_data = carBinderManager.getAllCarsInfo ();
        operator_base_table.setItems(cars_data);
    }

        class TableContextMenu extends ContextMenu {
            private ContextMenu INSTANCE = null;
            public TableContextMenu() {

                MenuItem update = new MenuItem("修改");
                MenuItem refresh = new MenuItem("刷新");


                update.setOnAction(new Update());
                refresh.setOnAction (new Refresh ());

                getItems().addAll(update, refresh);
            }

            public ContextMenu getInstance() {
                if (INSTANCE == null) {
                    INSTANCE = new TableContextMenu ();
                }
                return INSTANCE;
            }

            class Update implements EventHandler<ActionEvent> {
                @Override
                public void handle(ActionEvent event) {
                    TablePosition tablePosition = operator_base_table.getFocusModel ().getFocusedCell ();
                    TableColumn<CarBinder,String> column = tablePosition.getTableColumn ();
                    int row = tablePosition.getRow ();
                    if(operator_base_table.getFocusModel ().getFocusedCell ().getColumn () > 4) {
                        operator_base_table.setEditable(true);
                        operator_base_table.edit(row,column);
                        column.setCellFactory(TextFieldTableCell.forTableColumn());
                    }
                }
            }

            class Refresh implements EventHandler<ActionEvent> {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        loadTable ();
                    } catch (SQLException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }
}
