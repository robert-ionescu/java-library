package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Vector;

public class MybooksController implements Initializable {
    @FXML
    private Button buttonBack;
    @FXML
    private TableView<BookIssue> table;
    @FXML
    private TableColumn<BookIssue,Date> issueDate;
    @FXML
    private TableColumn<BookIssue,Date> returnDate;
    @FXML
    private TableColumn<BookIssue,String> author;
    @FXML
    private TableColumn<BookIssue,String> title;

    private User user;
    private ObservableList<BookIssue> books=FXCollections.observableArrayList();



    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneManager.sceneMain(event, user);
            }
        });
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BookIssue returnBook=table.getSelectionModel().getSelectedItem();
                Utils.returnBook(returnBook);
               table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setBooks(ObservableList<BookIssue> Books){
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        issueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        table.setItems(Books);}
    public void setUser(User user){this.user=user;}
}
