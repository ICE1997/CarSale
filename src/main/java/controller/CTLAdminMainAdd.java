package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import useraccount.OperatorAccountManager;
import usertype.Operator;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLAdminMainAdd implements Initializable {
    @FXML
    private TextField admin_add_input_work_num;
    @FXML
    private TextField admin_add_input_password;
    @FXML
    private TextField admin_add_input_name;
    @FXML
    private TextField admin_add_input_gender;
    @FXML
    private TextField admin_add_input_age;
    @FXML
    private TextField admin_add_input_birthday;
    @FXML
    private TextField admin_add_input_card_num;
    @FXML
    private TextField admin_add_input_phone_num;
    @FXML
    private TextArea admin_add_input_address;
    @FXML
    private CheckBox admin_add_checkbox_base;
    @FXML
    private CheckBox admin_add_checkbox_stock;
    @FXML
    private CheckBox admin_add_checkbox_sale;
    @FXML
    private CheckBox admin_add_checkbox_wm;
    @FXML
    private Button admin_add_btn_save;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin_add_btn_save.setOnAction(new SaveEvent());
    }

    class SaveEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String workNum = admin_add_input_work_num.getText();
            String password = admin_add_input_password.getText();
            String name = admin_add_input_name.getText();
            boolean gender = !"男".equals(admin_add_input_gender.getText());
            String age = admin_add_input_age.getText();
            String birthday = admin_add_input_birthday.getText();
            String id_num = admin_add_input_card_num.getText();
            String phone_num = admin_add_input_phone_num.getText();
            String address = admin_add_input_address.getText();
            boolean baseP = admin_add_checkbox_base.isSelected();
            boolean stockP = admin_add_checkbox_stock.isSelected();
            boolean saleP = admin_add_checkbox_sale.isSelected();
            boolean wmP = admin_add_checkbox_wm.isSelected();
            Operator operator = new Operator(workNum, name, gender, age, birthday, id_num, phone_num, address, baseP, stockP, saleP, wmP);
            try {
                OperatorAccountManager operatorAccountManager = new OperatorAccountManager();
                operatorAccountManager.addOperator(operator, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
