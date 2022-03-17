package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label label_username;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField tf_search;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> language;
    @FXML
    private TableColumn<Book,String> author;
    @FXML
    private TableColumn<Book,String> title;

    @FXML
    private Button button_adminPanel;

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

        comboBox.setItems(FXCollections.observableArrayList("Profile","My Books","Log out"));
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String comboBoxValue=getComboBoxInfo(event);
                if(comboBoxValue.equals("Log out"))
                {
                    SceneManager.sceneLogIn(event);
                }
                if(comboBoxValue.equals("Profile"))
                {
                    SceneManager.sceneProfil(event,user);
                }
                if(comboBoxValue.equals("My Books"))
                {
                    SceneManager.sceneMyBooks(event,user);
                }
            }
        });

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Book borrowBook=table.getSelectionModel().getSelectedItem();
                Utils.borrowBook(borrowBook,user.getUser_id());
            }
        });
        button_adminPanel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               SceneManager.sceneAdmin(event, user);
            }
        });

    }
    public void hideAdminButton(){button_adminPanel.setVisible(false);}


    public void setUsernameLabel(String usernameLabel){this.label_username.setText(usernameLabel);}
    public void setUser(User user)
    {
        this.user=user;
    }
    public String getComboBoxInfo(ActionEvent event)
    {
        return comboBox.getValue();
    }


}