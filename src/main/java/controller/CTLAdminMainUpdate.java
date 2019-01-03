package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import useraccount.OperatorAccount;
import useraccount.OperatorAccountManager;
import usertype.Operator;
import util.StaticDataManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLAdminMainUpdate implements Initializable {
    @FXML
    private TextField admin_update_input_work_num;
    @FXML
    private TextField admin_update_input_password;
    @FXML
    private TextField admin_update_input_name;
    @FXML
    private TextField admin_update_input_gender;
    @FXML
    private TextField admin_update_input_age;
    @FXML
    private TextField admin_update_input_birthday;
    @FXML
    private TextField admin_update_input_card_num;
    @FXML
    private TextField admin_update_input_phone_num;
    @FXML
    private TextArea admin_update_input_address;
    @FXML
    private CheckBox  admin_update_checkbox_base;
    @FXML
    private CheckBox  admin_update_checkbox_stock;
    @FXML
    private CheckBox  admin_update_checkbox_sale;
    @FXML
    private CheckBox  admin_update_checkbox_wm;
    @FXML
    private Button    admin_update_btn_save;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin_update_btn_save.setOnAction(new SaveEvent());
        try {
            initSurf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void initSurf() throws SQLException {
        Operator operator = StaticDataManager.getOperator();
        if(operator != null) {
            admin_update_input_work_num.setText(operator.getWorkNum());
            admin_update_input_name.setText(operator.getName());

            if(operator.isGirl()){
                admin_update_input_gender.setText("女");
            }else {
                admin_update_input_gender.setText("男");
            }
            OperatorAccount operatorAccount = new OperatorAccount(operator.getWorkNum(),StaticDataManager.getConnection());
//            System.out.println(operatorAccount.getPassword());
            admin_update_input_password.setText(operatorAccount.getPassword());
            admin_update_input_age.setText(operator.getWorkNum());
            admin_update_input_birthday.setText(operator.getBirthday());
            admin_update_input_card_num.setText(operator.getId_num());
            admin_update_input_phone_num.setText(operator.getPhone_num());
            admin_update_input_address.setText(operator.getAddress());

            if(operator.getBaseP()) {
                admin_update_checkbox_base.setSelected(true);
            }else {
                admin_update_checkbox_base.setSelected(false);
            }

            if(operator.getStockP()) {
                admin_update_checkbox_stock.setSelected(true);
            }else {
                admin_update_checkbox_stock.setSelected(false);
            }

            if(operator.getSaleP()) {
                admin_update_checkbox_sale.setSelected(true);
            }else {
                admin_update_checkbox_sale.setSelected(false);
            }

            if(operator.getWmP()) {
                admin_update_checkbox_wm.setSelected(true);
            }else {
                admin_update_checkbox_wm.setSelected(false);
            }

        }else {
            System.out.println("Operator为空！");
        }
    }
   class SaveEvent implements EventHandler<ActionEvent> {

       @Override
       public void handle(ActionEvent event) {
           try {
               updateOperator();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       void updateOperator() throws SQLException {
           String workNum = admin_update_input_work_num.getText();
           String password = admin_update_input_password.getText();
           String name = admin_update_input_name.getText();
           boolean gender;
           if ("男".equals(admin_update_input_gender.getText())){
               gender = false;
           }else {
               gender = true;
           }
           String age = admin_update_input_age.getText();
           String birthday = admin_update_input_birthday.getText();
           String id_num = admin_update_input_card_num.getText();
           String phone_num = admin_update_input_phone_num.getText();
           String address = admin_update_input_address.getText();

           Boolean baseP ;
           if(admin_update_checkbox_base.isSelected()) {
               baseP = true;
           }else {
               baseP = false;
           }
           Boolean stockP;
           if(admin_update_checkbox_stock.isSelected()) {
               stockP = true;
           }else {
               stockP = false;
           }
           Boolean saleP;
           if(admin_update_checkbox_sale.isSelected()) {
               saleP = true;
           }else {
               saleP = false;
           }
           Boolean wmP;
           if(admin_update_checkbox_wm.isSelected()) {
               wmP = true;
           }else {
               wmP = false;
           }
           Operator operator = new Operator(workNum,name,gender,age,birthday,id_num,phone_num,address,baseP,stockP,saleP,wmP);
           OperatorAccountManager operatorAccountManager = new OperatorAccountManager();
           operatorAccountManager.updateOperator(operator,password);

       }
   }

}

