package model.dao;

import model.entities.users.User;

/**
 * Created by troll on 16.08.2017.
 */
public interface UserDaoInterface extends GenericDao<User> {

    User selectByEmailPassword(String email, String password);
}
