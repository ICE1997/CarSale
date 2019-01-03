package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.StaticData;
import usertype.Operator;
import java.net.URL;
import java.util.ResourceBundle;

public class CTLAdminMainAdd implements Initializable {
    @FXML
    private TextField add_input_work_num;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Operator operator = StaticData.getOperator ();
        add_input_work_num.setText (operator.getWorkNum ());
    }

}
