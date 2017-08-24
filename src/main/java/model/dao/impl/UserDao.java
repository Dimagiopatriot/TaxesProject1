package model.dao.impl;

import model.dao.UserDaoInterface;
import model.dao.connection.SQLConnector;
import model.entities.users.User;
import model.entities.users.UserBuilder;

import java.sql.*;
import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public class UserDao implements UserDaoInterface {

    private static final String DELETE_QUERY = "DELETE from firstproject.user WHERE id=?;";
    private static final String UPDATE_QUERY = "UPDATE firstproject.user SET password=?, email=?, isAdmin=? WHERE id=?;";
    private static final String INSERT_QUERY = "INSERT INTO firstproject.user(password, email, isAdmin) VALUES(?, ?, ?);";
    private static final String SELECT_QUERY = "SELECT * FROM firstproject.user WHERE id=?;";
    private static final String SELECT_EMAIL_PASS_QUERY = "SELECT * FROM firstproject.user WHERE email=? AND password=?";

    public boolean delete(int id){
        return delete(id, DELETE_QUERY);
    }

    @Override
    public boolean update(User user) {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setBoolean(3, user.isAdmin());
            statement.setInt(4, user.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean insert(User user) {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setBoolean(3, user.isAdmin());
            updatedRow = statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    @Override
    public Optional<User> select(int id) {
        Optional<User> user = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            user = Optional.of(buildUser(resultSet));
        } catch (SQLException e) {
            return user;
        }

        return user;
    }

    @Override
    public Optional<User> selectByEmailPassword(String email, String password) {
        Optional<User> user = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_EMAIL_PASS_QUERY)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            user = Optional.of(buildUser(resultSet));
        } catch (SQLException e) {
            //e.printStackTrace();
            return user;
        }
        return user;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new UserBuilder().setId(resultSet.getInt(1))
                .setPassword(resultSet.getString(2))
                .setEmail(resultSet.getString(3))
                .setIsAdmin(resultSet.getBoolean(4))
                .createUser();
    }
}
