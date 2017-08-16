package model.entities.users;

/**
 * Created by troll on 14.08.2017.
 */
public class Admin extends User{

    Admin(String email, String password) {
        super(email, password);
        isAdmin = true;
    }
}
