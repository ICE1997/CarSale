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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Database;
import useraccount.OperatorAccountManager;
import usertype.Operator;
import util.StaticDataManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class CTLAdminMain implements Initializable {
    private Connection conn;
    @FXML
    private SplitPane admin_main;//管理员主界面
    @FXML
    private ListView<GridPane> admin_main_list_simple_operator;//操作员信息列表
    @FXML
    private MenuItem admin_main_menu_item_add;//增加操作员菜单栏
    @FXML
    private MenuItem admin_main_menu_item_delete;//删除操作员菜单栏
    @FXML
    private MenuItem admin_main_menu_item_update;//更新操作员菜单栏
    @FXML
    private MenuItem admin_main_menu_item_refresh;//刷新菜单项
    private ObservableList<GridPane> gridPanes = FXCollections.observableArrayList();
    private GridPane selected_pane = null;
    private String selected_number = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conn = Database.getConnection();
            StaticDataManager.conn = conn;
            admin_main_menu_item_refresh.setOnAction(new RefreshEvent());
            admin_main_menu_item_add.setOnAction(new AddNewOperatorEvent());
            admin_main_menu_item_delete.setOnAction(new DeleteItemEvent());
            admin_main_menu_item_update.setOnAction(new UpdateOperatorEvent());
            displayOperator();
            loadAllOperators();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayOperator() throws IOException {
        String ADMIN_MAIN_SEC_DISPLAY = "../app/layouts/admin_main_display_operator.fxml";
        Parent secDisplay = FXMLLoader.load((getClass().getResource(ADMIN_MAIN_SEC_DISPLAY)));
        admin_main.getItems().set(1, secDisplay);
    }

    private void loadAllOperators() throws SQLException {
        admin_main_list_simple_operator.setOnMouseClicked(new SelectItemEvent());
        admin_main_list_simple_operator.setItems(gridPanes);
        OperatorAccountManager operatorAccountManager = new OperatorAccountManager();
        LinkedList<Operator> operators = operatorAccountManager.getAllOperator();
        StaticDataManager.operators_from_database = operators;
        for (Operator operator : operators) {

            GridPane gridPane = new GridPane();

            Label number = new Label(operator.getWorkNum());
            Label name = new Label(operator.getName());
            Label age = new Label(operator.getAge());
            Label gender;

            if (operator.isGirl()) {
                gender = new Label("女");
            } else {
                gender = new Label("男");
            }

            for (int j = 0; j < 4; j++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(25);
                colConstraints.setHgrow(Priority.ALWAYS);
                colConstraints.setHalignment(HPos.CENTER);
                gridPane.getColumnConstraints().add(colConstraints);
            }

            gridPane.add(number, 0, 0);
            gridPane.add(name, 1, 0);
            gridPane.add(gender, 2, 0);
            gridPane.add(age, 3, 0);
            gridPanes.add(gridPane);
        }
    }

    class SelectItemEvent implements EventHandler<MouseEvent> {
        private ContextMenu contextMenu = null;

        @Override
        public void handle(MouseEvent event) {
            if (admin_main_list_simple_operator != null) {
                selected_pane = admin_main_list_simple_operator.getSelectionModel().getSelectedItem();
            }
            if (selected_pane != null) {
                ObservableList<Node> label = selected_pane.getChildren();
                selected_number = ((Label) (label.get(0))).getText();
            } else {
                System.out.println("Bug???");
            }

            if (event.getButton() == MouseButton.PRIMARY) {
                try {
                    StaticDataManager.selected_operator_work_num = selected_number;
                    displayOperator();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (contextMenu == null) {
                contextMenu = new AdminContextMenu().getInstance();
            }

            if (selected_pane != null) {
                selected_pane.setOnContextMenuRequested((ContextMenuEvent event1) -> contextMenu.show(selected_pane, event1.getScreenX(), event1.getScreenY()));
            }
        }

        class AdminContextMenu extends ContextMenu {
            private AdminContextMenu INSTANCE = null;

            public AdminContextMenu() {
                MenuItem add = new MenuItem("新增");
                MenuItem update = new MenuItem("修改");
                MenuItem delete = new MenuItem("删除");

                delete.setOnAction(new DeleteItemEvent());
                add.setOnAction(new AddNewOperatorEvent());
                update.setOnAction(new UpdateOperatorEvent());

                getItems().addAll(add, update, delete);
            }

            public AdminContextMenu getInstance() {
                if (INSTANCE == null) {
                    INSTANCE = new AdminContextMenu();
                }
                return INSTANCE;
            }
        }
    }

    class DeleteItemEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                OperatorAccountManager operatorAccountManager = new OperatorAccountManager();
                if (selected_pane != null) {
                    if (operatorAccountManager.isExist(selected_number)) {
                        operatorAccountManager.removeOperator(selected_number);
                        admin_main_list_simple_operator.getItems().remove(selected_pane);
                        selected_pane = null;
                    } else {
                        System.out.println("此用户不存在");
                    }
                } else {
                    System.out.println("未选择要删除的节点！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    class AddNewOperatorEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                changeToAddOperatorSurface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void changeToAddOperatorSurface() throws IOException {
            String ADMIN_MAIN_SEC_ADD = "../app/layouts/admin_main_add_operator.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource(ADMIN_MAIN_SEC_ADD)));
            Parent secAdd = fxmlLoader.load();
            admin_main.getItems().set(1, secAdd);
        }
    }

    class UpdateOperatorEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (selected_pane != null) {
                    changeToUpdateOperatorSurface();
                } else {
                    System.out.println("未选择要修改的节点！");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void changeToUpdateOperatorSurface() throws IOException {
            String ADMIN_MAIN_SEC_UPDATE = "../app/layouts/admin_main_update_operator.fxml";
            Parent secUpdate = FXMLLoader.load((getClass().getResource(ADMIN_MAIN_SEC_UPDATE)));
            admin_main.getItems().set(1, secUpdate);
        }
    }

    class RefreshEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                selected_pane = null;
                admin_main_list_simple_operator.getItems().remove(0, admin_main_list_simple_operator.getItems().size());
                loadAllOperators();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
