package model.entities.users;

/**
 * Created by troll on 16.08.2017.
 */
public class UserBuilder {

    private Integer id = 0;
    private String email = "example@email.com";
    private String password = "111111";
    private boolean isAdmin = false;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public User createUser() {
        User user;
        if (isAdmin)
            user = new Admin(email, password);
        else
            user = new User(email, password);
        user.setId(id);
        return user;
    }

}
