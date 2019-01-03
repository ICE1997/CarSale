package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import usertype.Operator;
import util.StaticDataManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CTLAdminMainDisplay implements Initializable {
    @FXML
    private Label admin_display_label_work_num;
    @FXML
    private Label admin_display_label_name;
    @FXML
    private Label admin_display_label_gender;
    @FXML
    private Label admin_display_label_age;
    @FXML
    private Label admin_display_label_birthday;
    @FXML
    private Label admin_display_label_card_num;
    @FXML
    private Label admin_display_label_phone_num;
    @FXML
    private Label admin_display_label_address;
    @FXML
    private Label admin_display_label_base;
    @FXML
    private Label admin_display_label_stock;
    @FXML
    private Label admin_display_label_sale;
    @FXML
    private Label admin_display_label_wm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSurf();
    }

    private void initSurf() {
        Operator operator = StaticDataManager.getOperator();
        if (operator != null) {
            admin_display_label_work_num.setText(operator.getWorkNum());
            admin_display_label_name.setText(operator.getName());

            if (operator.isGirl()) {
                admin_display_label_gender.setText("女");
            } else {
                admin_display_label_gender.setText("男");
            }

            admin_display_label_age.setText(operator.getWorkNum());
            admin_display_label_birthday.setText(operator.getBirthday());
            admin_display_label_card_num.setText(operator.getId_num());
            admin_display_label_phone_num.setText(operator.getPhone_num());
            admin_display_label_address.setText(operator.getAddress());

            if (operator.getBaseP()) {
                admin_display_label_base.setVisible(true);
            } else {
                admin_display_label_base.setVisible(false);
            }

            if (operator.getStockP()) {
                admin_display_label_stock.setVisible(true);
            } else {
                admin_display_label_stock.setVisible(false);
            }

            if (operator.getSaleP()) {
                admin_display_label_sale.setVisible(true);
            } else {
                admin_display_label_sale.setVisible(false);
            }

            if (operator.getWmP()) {
                admin_display_label_wm.setVisible(true);
            } else {
                admin_display_label_wm.setVisible(false);
            }

        } else {
            System.out.println("Operator为空！");
        }
    }
}
