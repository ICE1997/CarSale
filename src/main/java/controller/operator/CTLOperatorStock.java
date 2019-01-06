package controller.operator;

import database.car.CarManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.car.Car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLOperatorStock implements Initializable {
    @FXML
    private ChoiceBox<String> stock_car_manufacturer;

    @FXML
    private ChoiceBox<String> stock_car_color;

    @FXML
    private Label stock_car_price;

    @FXML
    private TextField stock_car_num;

    @FXML
    private Button stock_car_buy;

    @FXML
    private ChoiceBox<String> stock_car_model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            init ();
            addListener ();
            stock_car_buy.setOnAction (new BuyCarEvent ());
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    void init() throws SQLException {
        stock_car_manufacturer.setItems (CarManager.getAllManufacturers ());
        stock_car_manufacturer.getSelectionModel ().select (0);

        stock_car_model.setItems (CarManager.getModels (stock_car_manufacturer.getValue ()));
        stock_car_model.getSelectionModel ().select (0);

        stock_car_price.setText (CarManager.getPrice (stock_car_manufacturer.getValue (),stock_car_model.getValue ()).get (0).toString ());

        stock_car_color.setItems (CarManager.getColors ());
        stock_car_color.getSelectionModel ().select (0);

    }

    void addListener() {
        stock_car_manufacturer.getSelectionModel ().selectedItemProperty ().addListener ((observable, oldValue, newValue) -> {
            System.out.println (newValue);
            try {
                stock_car_model.setItems (CarManager.getModels (newValue));
                stock_car_model.getSelectionModel ().select (0);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        });

        stock_car_model.getSelectionModel ().selectedItemProperty ().addListener ((observable, oldValue, newValue) -> {
            if (stock_car_manufacturer.getValue ()!=null&&newValue!=null) {
                try {
                    String price = CarManager.getPrice (stock_car_manufacturer.getValue (),newValue).get (0).toString ();
                    stock_car_price.setText (price);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }else {
                System.out.println ("bug");
            }
        });
    }

    class BuyCarEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if(stock_car_manufacturer.getSelectionModel ().isEmpty () || stock_car_model.getSelectionModel ().isEmpty () || stock_car_color.getSelectionModel ().isEmpty () || "".equals (stock_car_num.getText ())){
                System.out.println ("未正确选择~~~");
            }else {
                String res = stock_car_num.getText ();
                int num;
                if(res.matches ("-?[0-9]+(\\.[0-9]+)?")) {
                    num = Integer.parseInt (res);
                    for(int i = 0; i < num; i++) {
                        Car car = new Car(stock_car_manufacturer.getValue (),stock_car_model.getValue (),Integer.parseInt (stock_car_price.getText ()),stock_car_color.getValue ());
                        try {
                            CarManager.addCar (car);
                        } catch (SQLException e) {
                            e.printStackTrace ();
                        }
                    }
                }else {
                    System.out.println ("数量输入有误！");
                }
            }
        }
    }
}
