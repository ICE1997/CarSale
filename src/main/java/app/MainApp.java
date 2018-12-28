package app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {
    private final String LAYOUT = "layouts/main.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load (getClass ().getResource (LAYOUT));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setOnCloseRequest (new CloseWindowEvent ());
        primaryStage.setResizable (false);
        primaryStage.show ();
    }

    class CloseWindowEvent implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            new ConfirmationDialog ();
        }
    }

    class ConfirmationDialog {
        Alert confirmation;
        public ConfirmationDialog() {
            this.confirmation = new Alert (Alert.AlertType.CONFIRMATION,"退出？");
        }
    }
}
