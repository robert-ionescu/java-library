package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SceneManager {

    public static void sceneMyBooks(ActionEvent event,User user)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("MyBooks.fxml"));
            root=loader.load();
            MybooksController mybooksController=loader.getController();
            ObservableList<BookIssue> bookIssues=Utils.getMyBooks(user);
            mybooksController.setBooks(bookIssues);
            mybooksController.setUser(user);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    public static void sceneProfil(ActionEvent event, User user)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("Profile.fxml"));
            root=loader.load();
            ProfileController profileController=loader.getController();
            profileController.setUsername(user.getUsername());
            profileController.setPassword(user.getPassword());
            profileController.setUser(user);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void sceneSignUp(ActionEvent event)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("SignUpController.fxml"));
            root=loader.load();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void sceneLogIn(ActionEvent event)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("LogIn.fxml"));
            root=loader.load();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void sceneMain(ActionEvent event,User user)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("Main.fxml"));
            root=loader.load();
            MainController mainController=loader.getController();
            mainController.setUser(user);
            if (!user.isAdmin())
            {
                mainController.hideAdminButton();
            }
            mainController.setUsernameLabel(user.getUsername());
        }catch (IOException | SQLException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void sceneAdmin(ActionEvent event, User user)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("Admin.fxml"));
            root=loader.load();
            AdminController adminController=loader.getController();
            adminController.setUser(user);
            adminController.setUsernameLabel(user.getUsername());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void sceneAddBook(ActionEvent event, User user)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("AddBook.fxml"));
            root=loader.load();
            AddBookController addBookController=loader.getController();
            addBookController.setUser(user);
            addBookController.setUsernameLabel(user.getUsername());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void sceneEditBook(MouseEvent event, User user, Book book)
    {
        Parent root=null;
        try{
            FXMLLoader loader=new FXMLLoader(Utils.class.getResource("EditBook.fxml"));
            root=loader.load();
            EditBookController editBookController=loader.getController();
            editBookController.setUser(user);
            editBookController.setBook(book);
            editBookController.setUsernameLabel(user.getUsername());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
}
