package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private Button button_back;

    @FXML
    private Button button_save;

    @FXML
    private Label label_username;

    @FXML
    private TextField tf_author;

    @FXML
    private TextField tf_language;

    @FXML
    private TextField tf_title;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneManager.sceneAdmin(event, user);
            }
        });
        button_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    Utils.addBook(tf_title.getText(), tf_author.getText(), tf_language.getText());


            }
        });
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    public void setUsernameLabel(String usernameLabel){this.label_username.setText(usernameLabel);}
}
