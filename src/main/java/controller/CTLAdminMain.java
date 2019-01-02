package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CTLAdminMain implements Initializable {
    @FXML
    private ListView<GridPane> info_simple_operator;//操作员信息列表

    @FXML
    private SplitPane admin_main;

    private final String ADMIN_MAIN_SEC = "../app/layouts/admin_main_update_operator.fxml";
    private ObservableList<GridPane> gridPanes = FXCollections.observableArrayList();
    private GridPane selected_pane = null;
    private String selected_number = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent sec = FXMLLoader.load (getClass ().getResource (ADMIN_MAIN_SEC));
            admin_main.getItems ().add (sec);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        loadAllOperators();
    }

    private void loadAllOperators() {
        info_simple_operator.setOnMouseClicked(new SelectItemEvent());
        info_simple_operator.setItems(gridPanes);
        for (int i = 0; i < 100; i++) {
            GridPane gridPane = new GridPane();
            Label number = new Label("2017211600");
            Label name = new Label("何福生");
            Label sex = new Label("男");
            Label age = new Label(Integer.toString(i));

            for (int j = 0; j < 4; j++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(25);
                colConstraints.setHgrow(Priority.ALWAYS);
                colConstraints.setHalignment(HPos.CENTER);
                gridPane.getColumnConstraints().add(colConstraints);
            }

            gridPane.add(number, 0, 0);
            gridPane.add(name, 1, 0);
            gridPane.add(sex, 2, 0);
            gridPane.add(age, 3, 0);
            gridPanes.add(gridPane);

        }
    }




    class SelectItemEvent implements EventHandler<MouseEvent>{
        private ContextMenu contextMenu = null;

        @Override
        public void handle(MouseEvent event) {
            selected_pane = info_simple_operator.getSelectionModel().getSelectedItem();
            ObservableList<Node> label = selected_pane.getChildren();
            selected_number = ((Label)(label.get(0))).getText();
            System.out.println(info_simple_operator.getSelectionModel().getSelectedItems());
            if(contextMenu==null) {
                contextMenu = new AdminContextMenu().getInstance();
            }
            selected_pane.setOnContextMenuRequested((ContextMenuEvent event1) -> contextMenu.show(selected_pane, event1.getScreenX(), event1.getScreenY()));
        }

        class AdminContextMenu extends ContextMenu {

            private AdminContextMenu INSTANCE = null;
            public AdminContextMenu() {
                MenuItem add = new MenuItem("新增");
                MenuItem delete = new MenuItem("修改");
                MenuItem refresh = new MenuItem("删除");
                refresh.setOnAction(new DeleteItemEvent());
                getItems().addAll(add,delete,refresh);
            }

            public AdminContextMenu getInstance() {
                if(INSTANCE == null) {
                    INSTANCE = new AdminContextMenu();
                }
                return INSTANCE;
            }
        }
    }

    class DeleteItemEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (selected_pane != null) {
                info_simple_operator.getItems().remove(selected_pane);
                selected_pane = null;
            }else {
                System.out.println("未选择要删除的节点！");
            }
        }
    }
}
