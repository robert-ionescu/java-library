package sample;

import javafx.event.ActionEvent;

public class UserManager {

    public static User logInUser(ActionEvent event, User user)
    {
        return Utils.logInVerification(user.getUsername(), user.getPassword());
    }

    public static User signupUser(ActionEvent event,User user)
    {
        return Utils.signUpProcess(user);

    }
}
