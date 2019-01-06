package controller.operator;

import database.car.CarBinderManager;
import database.car.WarehouseManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.car.CarBinder;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLOperatorSale implements Initializable {
    @FXML
    private ChoiceBox<String> car_manufacturer;
    @FXML
    private ChoiceBox<String> car_model;
    @FXML
    private ChoiceBox<String> car_color;
    @FXML
    private TextField client_name;
    @FXML
    private TextField client_gender;
    @FXML
    private TextField client_age;
    @FXML
    private TextField client_phone;
    @FXML
    private TextField car_sale_price;
    @FXML
    private Label car_benefit;
    @FXML
    private Button car_buy;
    @FXML
    private Label origin_price;
    @FXML
    private GridPane operator_sale;

    private int id;

    private ContextMenu contextMenu = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(WarehouseManager.getRestCarsNum () >0 ){
                init ();
            }else {
                car_buy.setDisable (true);
                car_buy.setText ("售罄");
            }
            operator_sale.setOnContextMenuRequested (event -> {
                if (contextMenu == null) {
                    contextMenu = new ContextMyMenu ().getInstance ();
                }
                contextMenu.show (operator_sale,event.getScreenX(),event.getScreenY ());
            });

            addListener ();
            car_buy.setOnAction (new SaleEvent ());
            car_sale_price.setOnKeyReleased (new GetBenefitEvent ());
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    void init() throws SQLException {
        car_manufacturer.setItems (WarehouseManager.getAllManufacturers ());
        car_manufacturer.getSelectionModel ().select (0);

        car_model.setItems (WarehouseManager.getModels (car_manufacturer.getValue ()));
        car_model.getSelectionModel ().select (0);

        origin_price.setText (WarehouseManager.getPrice (car_manufacturer.getValue (),car_model.getValue ()).get (0).toString ());
        id = WarehouseManager.getId (car_manufacturer.getValue (),car_model.getValue ());

        car_color.setItems (WarehouseManager.getColors ());
        car_color.getSelectionModel ().select (0);

    }

   void addListener() {
        car_manufacturer.getSelectionModel ().selectedItemProperty ().addListener ((observable, oldValue, newValue) -> {
            try {
                car_model.setItems (WarehouseManager.getModels (newValue));
                car_model.getSelectionModel ().select (0);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });

        car_model.getSelectionModel ().selectedItemProperty ().addListener ((observable, oldValue, newValue) -> {
            if (car_manufacturer.getValue ()!=null&&newValue!=null) {
                try {
                    origin_price.setText (WarehouseManager.getPrice (car_manufacturer.getValue (),newValue).get (0).toString ());
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }else {
                System.out.println ("bug");
            }
        });
    }

    class GetBenefitEvent implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode () == KeyCode.ENTER) {
                car_benefit.setText (Integer.toString (Integer.parseInt (car_sale_price.getText ())-Integer.parseInt (origin_price.getText ())));
            }
        }
    }

    class SaleEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            CarBinder carBinder = new CarBinder (Integer.toString (id),car_manufacturer.getValue (),car_color.getValue (),car_model.getValue (),Integer.parseInt (car_sale_price.getText ()),true,client_name.getText (),Integer.parseInt (client_age.getText ()),client_gender.getText (),client_phone.getText ());
            CarBinderManager carBinderManager = new CarBinderManager ();
            try {
                carBinderManager.addCarInfo (carBinder);

                if(WarehouseManager.getRestCarsNum () > 0) {
                    init ();
                    car_buy.setDisable (false);
                    car_buy.setText ("售出");
                }else {
                    car_manufacturer.getSelectionModel ().clearSelection ();
                    car_color.getSelectionModel ().clearSelection ();
                    car_model.getSelectionModel ().clearSelection ();
                    car_buy.setDisable (true);
                    car_buy.setText ("售罄");
                }

                client_age.setText ("");
                client_gender.setText ("");
                client_name.setText ("");
                client_phone.setText ("");
                origin_price.setText ("");
                car_sale_price.setText ("");
                car_benefit.setText ("");
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }
    }

    class ContextMyMenu extends ContextMenu {
        private ContextMenu INSTANCE = null;

        public ContextMyMenu() {
            MenuItem refresh = new MenuItem("刷新");
            refresh.setOnAction (new CTLOperatorSale.RefreshEvent ());
            getItems().addAll(refresh);
        }

        public ContextMenu getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new ContextMyMenu ();
            }
            return INSTANCE;
        }
    }

    class RefreshEvent implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                if(WarehouseManager.getRestCarsNum () > 0) {
                    init ();
                    car_buy.setDisable (false);
                    car_buy.setText ("售出");
                }else {
                    car_buy.setDisable (true);
                    car_buy.setText ("售罄");
                    System.out.println ("没车！");
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }
        }
}

