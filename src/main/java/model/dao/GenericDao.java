package model.dao;

import model.dao.connection.SQLConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public interface GenericDao<T> {

    default boolean delete(int id, String deleteQuery){
        boolean result;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)){

            statement.setInt(1, id);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    boolean update(T t);
    boolean insert(T t);
    Optional<T> select(int id);
}
