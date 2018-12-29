package app;

import event.CloseWindowEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import useraccount.AdminAccount;
import usertype.Admin;
import usertype.Operator;

public class OperatorMain extends Application {
    private final String LAYOUT = "layouts/operator_main.fxml";
    private Operator operator;

    Node tab_base;
    TabPane tabPane;

    public OperatorMain(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load (getClass ().getResource (LAYOUT));
            Scene scene = new Scene (root);
            primaryStage.setScene (scene);
            primaryStage.setOnCloseRequest(new CloseWindowEvent());
            primaryStage.show ();
           tabPane = (TabPane) scene.lookup("#tab_pane");
           tab_base = scene.lookup("#tab_base");
           tabPane.getTabs().remove(0);
    }
}
