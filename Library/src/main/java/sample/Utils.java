package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Vector;

public class Utils {



    public static void borrowBook(Book book,int userID)
    {
        Connection connection=null;
        PreparedStatement borrowBook=null;
        int  resultSet=0;
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 14);
        java.sql.Date futureDate=new Date(c.getTimeInMillis());
        try
        {

            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            PreparedStatement alreadyBorrowed=connection.prepareStatement("SELECT * from \"Book_Issues\" where \"Book_id\"=?");
            alreadyBorrowed.setInt(1,book.getBookId());

            if(!alreadyBorrowed.executeQuery().isBeforeFirst()) {
                borrowBook = connection.prepareStatement("INSERT INTO public.\"Book_Issues\"(\n" +
                        " \"Date_of_Issue\", \"Date_of_return\", \"Book_id\", \"User_id\")\n" +
                        "\tVALUES (?, ?, ?, ?)");
                borrowBook.setDate(1, date);
                borrowBook.setDate(2, futureDate);
                borrowBook.setInt(3, book.getBookId());
                borrowBook.setInt(4, userID);
                resultSet = borrowBook.executeUpdate();
                if (resultSet != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book Borrowed");
                    alert.show();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Book Already Borrowed");
                alert.show();
            }



        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {

            if(borrowBook!=null) {
                try {
                    borrowBook.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }

    }

    public static  ObservableList<Book> getBooks()
    {
        ObservableList<Book> books= FXCollections.observableArrayList();

        Connection connection=null;
        PreparedStatement psMyBooks=null;

        ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            psMyBooks=connection.prepareStatement("select * from \"Books\"");
            resultSet=psMyBooks.executeQuery();


            while(resultSet.next())
            {
                String title=resultSet.getString("Title");
                String Author=resultSet.getString("Author");
                String language=resultSet.getString("Language");

                int id=resultSet.getInt("Book_id");
                books.add(new Book(id,title,Author,language));
            }
            return books;


        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psMyBooks!=null) {
                try {
                    psMyBooks.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static void returnBook(BookIssue book)
    {
        Connection connection=null;
        PreparedStatement returnBook=null;
        int  resultSet=0;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            returnBook=connection.prepareStatement("DELETE FROM public.\"Book_Issues\"\n" +
                    "\tWHERE \"Issues_id\"=?;");
            returnBook.setInt(1,book.getIssueID());
            resultSet=returnBook.executeUpdate();
            if(resultSet!=0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Book Returned");
                alert.show();
            }



        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {

            if(returnBook!=null) {
                try {
                    returnBook.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }

    }
    public static void addBook(String title, String author, String language)
    {
        if(title.isEmpty() || author.isEmpty() || language.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("One or more fields are empty!");
            alert.show();
        }
        else {
            Connection connection = null;
            PreparedStatement addBook = null;
            int resultSet = 0;
            try {
                connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
                PreparedStatement checkForBook = connection.prepareStatement("select \"Book_id\" from \"Books\" where \"Author\" = ? and \"Title\" = ? and \"Language\" = ?;");
                checkForBook.setString(1, author);
                checkForBook.setString(2,title);
                checkForBook.setString(3,language);
                ResultSet foundBookResult = checkForBook.executeQuery();
                /*foundBookResult.next();
                int booknr = foundBookResult.getInt(1);
                System.out.println(booknr);*/
                if (!foundBookResult.isBeforeFirst()) {
                    addBook = connection.prepareStatement("insert into \"Books\"(\"Author\", \"Title\", \"Language\")\n" +
                            "values(?,?,?);");
                    addBook.setString(1, author);
                    addBook.setString(2, title);
                    addBook.setString(3, language);
                    resultSet = addBook.executeUpdate();
                    if (resultSet != 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Book Added");
                        alert.show();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book Has Already Been Added");
                    alert.show();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
    public static void editBook(Book book, String title, String author, String language)
    {
        if(title.isEmpty() || author.isEmpty() || language.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("One or more fields are empty!");
            alert.show();
        }
        else {
            Connection connection = null;
            PreparedStatement addBook = null;
            int resultSet = 0;
            try {
                connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
                addBook = connection.prepareStatement("update \"Books\" set \"Author\" = ?, \"Title\" = ?, \"Language\" =?\n" +
                        "where \"Author\" = ? and \"Title\" = ? and \"Language\" = ?;");
                addBook.setString(1, author);
                addBook.setString(2, title);
                addBook.setString(3, language);
                addBook.setString(4, book.getAuthor());
                addBook.setString(5, book.getTitle());
                addBook.setString(6, book.getLanguage());
                resultSet = addBook.executeUpdate();
                if (resultSet != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book Edited");
                    alert.show();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
    public static void deleteBook(Book book)
    {
        /*if(title.isEmpty() || author.isEmpty() || language.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("One or more fields are empty!");
            alert.show();
        }*/

            Connection connection = null;
            PreparedStatement deleteBookIssues = null;
            PreparedStatement deleteBook = null;
            int resultSet1 = 0;
            int resultSet2 = 0;
            try {
                connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
                /*PreparedStatement getBookId = connection.prepareStatement("select \"Book_id\" from \"Books\" where \"Author\" = ? and \"Title\" = ? and \"Language\" = ?;\t");
                getBookId.setString(1,author);
                getBookId.setString(2,title);
                getBookId.setString(3,language);
                ResultSet bookIdResult = getBookId.executeQuery();
                bookIdResult.next();
                int bookId = bookIdResult.getInt(1);*/
                deleteBookIssues = connection.prepareStatement("delete from \"Book_Issues\" where \"Book_id\" = ?;\n");

                deleteBookIssues.setInt(1, book.getBookId());
                deleteBook = connection.prepareStatement("delete from \"Books\" where \"Book_id\" = ?;");
                deleteBook.setInt(1, book.getBookId());
                resultSet2 = deleteBookIssues.executeUpdate();
                resultSet1 = deleteBook.executeUpdate();

                if (resultSet1 != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book Deleted");
                    alert.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("No such book exists");
                    alert.show();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }


    }

    public static ObservableList<BookIssue> getMyBooks(User user)
    {
        ObservableList<BookIssue> books= FXCollections.observableArrayList();

            Connection connection=null;
            PreparedStatement psMyBooks=null;

            ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
           psMyBooks=connection.prepareStatement("select \"Issues_id\", \"Date_of_Issue\",\"Date_of_return\",\"Title\",\"Author\" from \"Book_Issues\" inner join  \"Books\" on\n" +
                   "\"Books\".\"Book_id\"=\"Book_Issues\".\"Book_id\" where \"User_id\"=?");
            psMyBooks.setInt(1,user.getUser_id());
            resultSet=psMyBooks.executeQuery();


                while(resultSet.next())
                {
                    String title=resultSet.getString("Title");
                    String Author=resultSet.getString("Author");
                    Date issue=resultSet.getDate("Date_of_Issue");
                    Date returnDate=resultSet.getDate("Date_of_return");
                    int id=resultSet.getInt("Issues_id");
                    books.add(new BookIssue(id,returnDate,issue,title,Author));
                }
                return books;


        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psMyBooks!=null) {
                try {
                    psMyBooks.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }

    public  static void changePassword(ActionEvent event,User user,String newPassword)
    {
        if(user.getPassword().equals(newPassword))
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Password is the same");
            alert.show();
            return;
        }
        Connection connection=null;
        PreparedStatement psUpdate=null;

        int resultSet=0;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            psUpdate.setString(1,newPassword);
            psUpdate.setInt(2,user.getUser_id());
            resultSet=psUpdate.executeUpdate();

            if(resultSet!=0)
            {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Success");
                alert.show();
                user.setPassword(newPassword);
                return;
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Failed");
            alert.show();


        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {

            if(psUpdate!=null)
            {
                try
                {
                    psUpdate.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void changeUsername(ActionEvent event,User user,String newUsername)
    {
        if(user.getUsername().equals(newUsername))
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Username is the same");
            alert.show();
            return;
        }

        Connection connection=null;
        PreparedStatement psUpdate=null;

        int resultSet=0;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            psUpdate= connection.prepareStatement("update \"User\" set \"Username\"=? where \"User_id\"=?");
            psUpdate.setString(1,newUsername);
            psUpdate.setInt(2,user.getUser_id());
            resultSet=psUpdate.executeUpdate();

            if(resultSet!=0)
            {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Success");
                alert.show();
                user.setUsername(newUsername);
                return;
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Failed");
            alert.show();


        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {

            if(psUpdate!=null)
            {
                try
                {
                    psUpdate.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }


    }


    public static User logInVerification(String username, String password)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            preparedStatement=connection.prepareStatement("SELECT  \"Password\",\"User_id\",\"Admin\" From \"User\" WHERE \"Username\"=?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username does not exist");
                alert.show();
                return null;
            }
            if(resultSet.isBeforeFirst())
            {
                while(resultSet.next())
                {
                    String receivedPassword=resultSet.getString("password");
                    int receivedId=resultSet.getInt("User_id");
                    boolean receivedAdmin=resultSet.getBoolean("Admin");
                    if(receivedPassword.equals(password))
                    {
                        return new User(receivedId,username,receivedPassword,receivedAdmin);
                    }
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password does not match");
                    alert.show();
                    return null;
                }
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static User signUpProcess(User user)
    {

        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","Cocolino123");
            psCheckUserExists= connection.prepareStatement("SELECT * FROM \"User\" WHERE \"Username\" =?");
            psCheckUserExists.setString(1,user.getUsername());
            resultSet=psCheckUserExists.executeQuery();

            if(!resultSet.isBeforeFirst())
            {
                psInsert=connection.prepareStatement("INSERT INTO  \"User\" (\"Username\",\"Password\",\"Admin\") VALUES(?,?,?)");
                psInsert.setString(1,user.getUsername());
                psInsert.setString(2,user.getPassword());
                psInsert.setBoolean(3,user.isAdmin());
                psInsert.executeUpdate();
                psInsert=connection.prepareStatement("SELECT * FROM \"User\" WHERE \"Username\"=?");
                psInsert.setString(1, user.getUsername());
                resultSet=psCheckUserExists.executeQuery();
                if(resultSet.isBeforeFirst())
                {
                    resultSet.next();
                    String receivedPassword=resultSet.getString("Password");
                    int receivedId=resultSet.getInt("User_id");
                    boolean receivedAdmin=resultSet.getBoolean("Admin");
                    String username=resultSet.getString("Username");
                    return new User(receivedId,username,receivedPassword,receivedAdmin);
                }
                return null;
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User already exists");
            alert.show();

        }catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists!=null)
            {
                try
                {
                    psCheckUserExists.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psInsert!=null)
            {
                try
                {
                    psInsert.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
