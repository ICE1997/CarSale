package event;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

public class CloseWindowEvent implements EventHandler<WindowEvent> {
    @Override
    public void handle(WindowEvent event) {
        Alert confirmation = new Alert (Alert.AlertType.CONFIRMATION, "退出？");
        confirmation.initModality (Modality.APPLICATION_MODAL);
        confirmation.showAndWait ();
        if (confirmation.getResult () == ButtonType.OK) {
            Platform.exit ();
        } else {
            confirmation.close ();
            event.consume ();
        }
    }
}
