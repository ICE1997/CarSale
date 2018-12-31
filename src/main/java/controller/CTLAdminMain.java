package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CTLAdminMain implements Initializable {

    @FXML
    private ListView info_operator;

    private ObservableList<String> dataList = FXCollections.observableArrayList();

    private ObservableList<HBox> boxes = FXCollections.observableArrayList ();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        info_operator.setItems (boxes);

        for (int i = 0; i < 100; i++) {
            dataList.add (Integer.toString (i));
            HBox hBox = new HBox ();
            TextField textField = new TextField ("hfaa");
            hBox.getChildren ().add (textField);
            boxes.add (hBox);
        }
    }

    class OperatorInfoList {

    }
}
