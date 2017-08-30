package model.dao.impl;

import model.dao.TaxDaoInterface;
import model.dao.connection.SQLConnector;
import model.dao.exceptions.DaoException;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public class TaxDao implements TaxDaoInterface {

    private static final String DELETE_QUERY = "DELETE from firstproject.tax WHERE id=?;";
    private static final String UPDATE_QUERY = "UPDATE firstproject.tax SET taxPercent=?, name=? WHERE id=?;";
    private static final String UPDATE_QUERY_BY_NAME = "UPDATE firstproject.tax SET taxPercent=? WHERE name =?;";
    private static final String INSERT_QUERY = "INSERT INTO firstproject.tax(taxPercent, name) VALUES(?, ?);";
    private static final String SELECT_QUERY = "SELECT * FROM firstproject.tax WHERE id=?;";
    private static final String SELECT_QUERY_BY_NAME = "SELECT * FROM firstproject.tax WHERE name=?;";

    public boolean delete(int id){
        return delete(id, DELETE_QUERY);
    }

    @Override
    public boolean update(Tax tax) throws DaoException {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){

            statement.setDouble(1, tax.getTaxPercent());
            statement.setString(2, tax.getName());
            statement.setInt(3, tax.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean insert(Tax tax) throws DaoException {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setDouble(1, tax.getTaxPercent());
            statement.setString(2, tax.getName());
            updatedRow = statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    tax.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public Optional<Tax> select(int id) throws DaoException {
        Optional<Tax> income = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            income = Optional.of(buildTax(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return income;
    }

    @Override
    public boolean updateTaxByName(Tax tax) throws DaoException {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY_BY_NAME)){

            statement.setDouble(1, tax.getTaxPercent());
            statement.setString(2, tax.getName());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public void updateAllTaxes(List<Tax> taxList) throws DaoException {
        for (Tax tax: taxList){
            update(tax);
        }
    }

    @Override
    public void updateAllTaxesByName(List<Tax> taxList) throws DaoException {
        for (Tax tax: taxList){
            updateTaxByName(tax);
        }
    }

    @Override
    public Optional<Tax> selectByName(String name) throws DaoException {
        Optional<Tax> income = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            income = Optional.of(buildTax(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return income;
    }

    private Tax buildTax(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new TaxBuilder()
                .setId(resultSet.getInt(1))
                .setTaxPercent(resultSet.getDouble(2))
                .setName(resultSet.getString(3))
                .createTax();
    }
}
