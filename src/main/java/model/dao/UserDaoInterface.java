package model.dao;

import model.entities.users.User;

import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public interface UserDaoInterface extends GenericDao<User> {

    Optional<User> selectByEmailPassword(String email, String password);
}
