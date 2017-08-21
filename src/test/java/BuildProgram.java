import model.dao.impl.UserDao;
import model.entities.users.User;

import java.util.Optional;

/**
 * Created by troll on 15.08.2017.
 */
public class BuildProgram {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Optional<User> userOptional = userDao.selectByEmailPassword("vdkn@gmail.com", "435345");
        System.out.print(userOptional.isPresent());
    }
}
