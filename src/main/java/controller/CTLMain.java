package controller;

import app.AdminMain;
import app.OperatorMain;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import useraccount.AdminAccount;
import useraccount.BaserAccount;
import useraccount.OperatorAccount;
import usertype.Admin;
import usertype.BaseUsetType;
import usertype.Operator;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CTLMain implements Initializable {
    private final ToggleGroup user_type_group = new ToggleGroup ();//用户类型组
    private final int USER_TYPE_ADMIN = 0;//用户类型：操作员 代码0
    private final int USER_TYPE_OPERATOR = 1;//用户类型：管理员 代码1
    @FXML
    private TextField input_username;//用户名输入框
    @FXML
    private PasswordField input_password;//密码输入框
    @FXML
    private Label info_error_username;//用户名输入错误信息
    @FXML
    private Label info_error_password;//密码输入错误信息
    @FXML
    private Button btn_login_user;//用户登录按钮，包含操作员和管理员
    @FXML
    private RadioButton user_type_operator;//用户类型：操作员
    @FXML
    private RadioButton user_type_admin;//用户类型：管理员
    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDatabase ();
        initSurface ();
    }

    private void initSurface() {
        user_type_operator.setToggleGroup (user_type_group);
        user_type_operator.setSelected (true);
        user_type_admin.setToggleGroup (user_type_group);
        btn_login_user.setOnAction (new LoginEvent ());
    }

    private void initDatabase() {
        conn = Database.getConnction ();
    }

    private boolean userNameIsValid() {
//        System.out.println (input_username.getText ());
        return !"".equals (input_username.getText ());
    }

    private boolean passwordIsValid() {
//        System.out.println (input_password.getText ());
        return !"".equals (input_password.getText ());
    }

    private boolean inputIsValid() {
        if (!userNameIsValid () || !passwordIsValid ()) {
            if (userNameIsValid ()) {
                info_error_username.setVisible (false);
            } else {
                info_error_username.setVisible (true);
            }
            if (passwordIsValid ()) {
                info_error_password.setVisible (false);
            } else {
                info_error_password.setVisible (true);
            }
            return false;

        } else {
            info_error_username.setVisible (false);
            info_error_password.setVisible (false);
            return true;
        }
    }



    private int judgeUserType() {
        if (user_type_group.getSelectedToggle () == user_type_operator) {
            return USER_TYPE_OPERATOR;
        } else {
            return USER_TYPE_ADMIN;
        }
    }

    private boolean login() throws SQLException {
        BaseUsetType user = null;
        BaserAccount userAccount = null;

        if (inputIsValid ()) {
            if (judgeUserType () == USER_TYPE_OPERATOR) {
                user = new Operator (input_username.getText (), input_password.getText ());
                userAccount = new OperatorAccount ((Operator) user, conn);
            }

            if (judgeUserType () == USER_TYPE_ADMIN) {
                user = new Admin (input_username.getText (), input_password.getText ());
                userAccount = new AdminAccount ((Admin) user, conn);
            }

            if (userAccount != null) {
                if (userAccount.isExist ()) {
                    if (userAccount.login ()) {
                        System.out.println ("Login Successfully!");
                        return true;
                    } else {
                        System.out.println ("Wrong Password!");
                        return false;
                    }
                } else {
                    System.out.println ("user isn't exist!");
                    return false;
                }
            }else {
                System.out.println ("This is a Error");
                return false;
            }
        }else {
            return false;
        }
    }

//    void goToAdminMain() throws Exception {
//        Application adminMain = new AdminMain ();
//        adminMain.start (new Stage ());
//        Stage stage = (Stage)input_username.getScene ().getWindow ();
//        stage.close ();
//    }

    void switchTo(Application app) throws Exception {
        app.start (new Stage ());
        Stage stage = (Stage)input_username.getScene ().getWindow ();
        stage.close ();
    }

    class LoginEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (login ()){
                    if (judgeUserType () == USER_TYPE_OPERATOR) {
                        switchTo (new OperatorMain ());
                    }
                    if (judgeUserType () == USER_TYPE_ADMIN) {
                        switchTo (new AdminMain ());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }
}

