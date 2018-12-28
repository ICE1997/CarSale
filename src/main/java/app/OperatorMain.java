package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import useraccount.AdminAccount;
import usertype.Admin;

public class OperatorMain extends Application {
    private final String LAYOUT = "layouts/operator_main.fxml";
//    OperatorMain(Admin admin) {
//
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load (getClass ().getResource (LAYOUT));
            Scene scene = new Scene (root);
            primaryStage.setScene (scene);
            primaryStage.show ();
    }
}
