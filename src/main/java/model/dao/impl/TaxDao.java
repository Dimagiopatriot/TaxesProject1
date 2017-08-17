package model.dao.impl;

import model.dao.GenericDao;
import model.dao.connection.SQLConnector;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;

import java.sql.*;
import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public class TaxDao implements GenericDao<Tax> {

    private static final String DELETE_QUERY = "DELETE from firstproject.tax WHERE id=?;";
    private static final String UPDATE_QUERY = "UPDATE firstproject.tax SET taxPercent=?, name=? WHERE id=?;";
    private static final String INSERT_QUERY = "INSERT INTO firstproject.user(password, email, isAdmin) VALUES(?, ?, ?);";
    private static final String SELECT_QUERY = "SELECT FROM firstproject.user WHERE id=?;";

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)){

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tax tax) {
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){

            statement.setDouble(1, tax.getTaxPercent());
            statement.setString(2, tax.getName());
            statement.setInt(3, tax.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Tax tax) {
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setDouble(1, tax.getTaxPercent());
            statement.setString(2, tax.getName());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    tax.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tax> select(int id) {
        Optional<Tax> income = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            income = Optional.of(buildTax(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return income;
    }

    private Tax buildTax(ResultSet resultSet) throws SQLException {
        return new TaxBuilder()
                .setId(resultSet.getInt(1))
                .setTaxPercent(resultSet.getDouble(2))
                .setName(resultSet.getString(3))
                .createTax();
    }
}
