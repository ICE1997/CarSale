package app;

import event.CloseWindowEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private final String LAYOUT = "layouts/main.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(LAYOUT));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new CloseWindowEvent());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
