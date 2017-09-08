package model.service;

import model.dao.DaoFactory;
import model.dao.exceptions.DaoException;
import model.entities.users.User;

import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory;

    UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService(DaoFactory.getInstance());
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean delete(int id) {
        boolean deleted;
        deleted = daoFactory.getUserDao().deleteByID(id);
        return deleted;
    }

    public boolean update(User user) throws DaoException {
        boolean updated;
        updated = daoFactory.getUserDao().update(user);
        return updated;
    }

    public boolean insert(User user) throws DaoException {
        boolean inserted;
        inserted = daoFactory.getUserDao().insert(user);
        return inserted;
    }

    public Optional<User> select(int id) throws DaoException {
        Optional<User> user;
        user = daoFactory.getUserDao().select(id);
        return user;
    }

    public Optional<User> selectByEmailPassword(String email, String password){
        Optional<User> user;
        user = daoFactory.getUserDao().selectByEmailPassword(email, password);
        return user;
    }

    public boolean isUserRegistered(String email, String password) {
        return selectByEmailPassword(email, password).isPresent();
    }

}
