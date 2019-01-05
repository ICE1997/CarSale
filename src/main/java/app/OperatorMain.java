package app;

import event.CloseWindowEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.usertype.Operator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OperatorMain extends Application {
    private final String LAYOUT = "layouts/operator/operator_main.fxml";
    private final String LAYOUT_TAB_BASE = "layouts/operator/operator_tab_base.fxml";
    private final String LAYOUT_TAB_STOCK = "layouts/operator/operator_tab_stock.fxml";
    private final String LAYOUT_TAB_SALE = "layouts/operator/operator_tab_sale.fxml";
    private final String LAYOUT_TAB_SW = "layouts/operator/operator_tab_sw.fxml";
    private TabPane tabPane;
    private Tab tab_base;
    private Tab tab_stock;
    private Tab tab_sale;
    private Tab tab_sw;
    private List<Tab> tabs = new LinkedList<>();
    private Operator operator;

    public OperatorMain(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(LAYOUT));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new CloseWindowEvent());
        primaryStage.show();
        tabPane = (TabPane) scene.lookup("#tab_pane");
        if(operator != null) {
            initSurface();
        }else {
            System.out.println("无返回用户");
        }
    }

    private void initSurface() throws IOException {
//        System.out.println(operator.getBaseP());
        if (operator.getBaseP()) {
            tab_base = new Tab("基础信息管理");
            tab_base.setId("tab_base");
            Parent layout_base = FXMLLoader.load(getClass().getResource(LAYOUT_TAB_BASE));
            tab_base.setContent(layout_base);
            tabs.add(tab_base);
        }

        if (operator.getStockP()) {
            tab_stock = new Tab("进货管理");
            tab_stock.setId("tab_stock");
            Parent layout_stock = FXMLLoader.load(getClass().getResource(LAYOUT_TAB_STOCK));
            tab_stock.setContent(layout_stock);
            tabs.add(tab_stock);
        }

        if (operator.getSaleP()) {
            tab_sale = new Tab("销售管理");
            tab_sale.setId("tab_sale");
            Parent layout_sale = FXMLLoader.load(getClass().getResource(LAYOUT_TAB_SALE));
            tab_sale.setContent(layout_sale);
            tabs.add(tab_sale);
        }

        if (operator.getWmP()) {
            tab_sw = new Tab("仓库管理");
            tab_sw.setId("tab_sw");
            Parent layout_sw = FXMLLoader.load(getClass().getResource(LAYOUT_TAB_SW));
            tab_sw.setContent(layout_sw);
            tabs.add(tab_sw);
        }

        if (!tabs.isEmpty()) {
            tabPane.getTabs().addAll(tabs);
        }
    }
}
