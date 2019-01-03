package app;

import event.CloseWindowEvent;
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
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight (600);
        primaryStage.setWidth (1200);
        primaryStage.setTitle ("系统维护");
        primaryStage.setOnCloseRequest(new CloseWindowEvent());
        primaryStage.show ();
    }
}
