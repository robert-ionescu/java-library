package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_confirm_password;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!tf_password.getText().equals(tf_confirm_password.getText()))
                {
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Password and Confirm Password do not match");
                    alert.show();
                    return;
                }
                User current=UserManager.signupUser(actionEvent,new User(-1,tf_username.getText(),tf_password.getText(),false));
                if(current!=null)
                {
                    SceneManager.sceneMain(actionEvent,current);
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                SceneManager.sceneLogIn(actionEvent);
            }
        });
    }
}
