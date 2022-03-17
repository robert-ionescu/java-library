package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_sign_up;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

                button_login.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        User current= UserManager.logInUser(actionEvent,new User(-1,tf_username.getText(),tf_password.getText(),false));
                        if(current!=null)
                        {
                            SceneManager.sceneMain(actionEvent,current);
                        };
                    }
                });
                button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        SceneManager.sceneSignUp(actionEvent);
                    }
                });
    }
}
