import model.dao.impl.UserDao;
import model.entities.users.User;
import model.entities.users.UserBuilder;

/**
 * Created by troll on 15.08.2017.
 */
public class BuildProgram {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setId(11)
                .setEmail("admin@gmail.com")
                .setPassword("admin")
                .setIsAdmin(true)
                .createUser();
        System.out.print(userDao.update(user));
    }
}
