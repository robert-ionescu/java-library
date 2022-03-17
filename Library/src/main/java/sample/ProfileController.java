package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Button button_back;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_changeUsername;
    @FXML
    private Button button_changePassword;

    private String oldUsername;
    private String oldPassword;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneManager.sceneMain(event,user);
            }
        });
        button_changeUsername.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeUsername(event,user,tf_username.getText());
            }
        });
        button_changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changePassword(event,user, tf_password.getText());
            }
        });


    }

    public void setUsername(String username){tf_username.setText(username);this.oldUsername=username;}
    public void setPassword(String password){tf_password.setText(password);this.oldPassword=password;}
    public void setUser(User user){this.user=user;}

}
