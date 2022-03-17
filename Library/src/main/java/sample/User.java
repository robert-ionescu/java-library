package sample;

import java.sql.*;

public class User {
    private int User_id;
    private String username;
    private String password;
    private boolean admin;


    public User(int user_id,String Username,String Password,boolean Admin)
    {
        User_id=user_id;
        username=Username;
        password=Password;
        admin=Admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_id() {
        return User_id;
    }

    public boolean isAdmin() throws SQLException {

 /*           Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library","postgres","postgres");
            PreparedStatement psCheckUserIsAdmin =connection.prepareStatement("select \"Admin\" from \"User\" where \"Username\" = ?");
            psCheckUserIsAdmin.setString(1, this.username);
            ResultSet resultSet = psCheckUserIsAdmin.executeQuery();
            resultSet.next();
        if (resultSet.getBoolean("Admin") == true)
        {
            return true;

        }
        else
        {
            return false;
        }*/
        return admin;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
