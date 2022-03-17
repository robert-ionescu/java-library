package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AdminController implements Initializable {

    @FXML
    private TableColumn<Book,String> author;

    @FXML
    private Button button_addbook;

    @FXML
    private Button button_back;

    @FXML
    private Label label_username;

    @FXML
    private TableColumn<Book,String> language;

    @FXML
    private TableView<Book> table;

    @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<Book,String> title;
    private User user;

    private ObservableList<Book> books=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        books=Utils.getBooks();
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        SortedList<Book> sortedBooks=Book.searchSetting(books,tf_search);
        sortedBooks.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedBooks);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Book editBook=table.getSelectionModel().getSelectedItem();
                SceneManager.sceneEditBook(mouseEvent, user, editBook);
                //change scene
            }
        });
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneManager.sceneMain(event, user);
            }
        });
        button_addbook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneManager.sceneAddBook(event, user);
            }
        });
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    public void setUsernameLabel(String usernameLabel){this.label_username.setText(usernameLabel);}
}
