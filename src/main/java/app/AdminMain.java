package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain extends Application {
    private final String LAYOUT = "layouts/admin_main.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load (getClass ().getResource (LAYOUT));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.show ();
    }
}
