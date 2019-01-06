package controller.operator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CTLOperatorWm implements Initializable {
    @FXML
    private PieChart carsInfo;


    ObservableList<PieChart.Data> data = FXCollections.observableArrayList ();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final Label caption = new Label ("");
        caption.setTextFill(Color.DARKBLUE);
        caption.setStyle("-fx-font: 24 arial;");
        data.add (new PieChart.Data ("已售",10));
        data.add (new PieChart.Data ("库存",80));
        data.add (new PieChart.Data ("进货",10));
        carsInfo.setData (data);
        carsInfo.setLegendSide (Side.LEFT);
        for (final  PieChart.Data dat : carsInfo.getData ())
            dat.getNode ().addEventHandler (MouseEvent.MOUSE_PRESSED, event -> {
                caption.setTranslateX (event.getSceneX ());
                caption.setTranslateY (event.getSceneY ());
                caption.setText (new StringBuilder ().append (String.valueOf (dat.getPieValue ())).append ("%").toString ());
            });
    }
}
