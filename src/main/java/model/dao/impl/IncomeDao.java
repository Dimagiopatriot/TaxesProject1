package model.dao.impl;

import model.dao.IncomeDaoInterface;
import model.dao.connection.SQLConnector;
import model.entities.incomes.Income;
import model.entities.incomes.IncomeBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public class IncomeDao implements IncomeDaoInterface {

    private static final String DELETE_QUERY = "DELETE from firstproject.income WHERE id=?;";
    private static final String UPDATE_QUERY = "UPDATE firstproject.income SET name=?, isPerMonth=?, income=? userId=?, " +
            "taxId=? WHERE id=?;";
    private static final String INSERT_QUERY = "INSERT INTO firstproject.income(name, isPerMonth, income, userId, taxId) " +
            "VALUES(?, ?, ?, ?, ?);";
    private static final String SELECT_QUERY = "SELECT FROM firstproject.income WHERE id=?;";
    private static final String SELECT_EMAIL_PASS_QUERY = "SELECT * FROM firstproject.income WHERE userId=?";

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
    public void update(Income income) {
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){

            statement.setString(1, income.getName());
            statement.setBoolean(2, income.isPerMonth());
            statement.setDouble(3, income.getIncome());
            statement.setInt(4, income.getUserId());
            statement.setInt(5, income.getTaxId());
            statement.setInt(6, income.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Income income) {
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, income.getName());
            statement.setBoolean(2, income.isPerMonth());
            statement.setDouble(3, income.getIncome());
            statement.setInt(4, income.getUserId());
            statement.setInt(5, income.getTaxId());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    income.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Income> select(int id) {
        Optional<Income> income = Optional.empty();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            income = Optional.of(buildIncome(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return income;
    }

    @Override
    public List<Income> selectAllIncomesForUser(int userId) {
        List<Income> incomes = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_EMAIL_PASS_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    incomes.add(buildIncome(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    private Income buildIncome(ResultSet resultSet) throws SQLException {
        return new IncomeBuilder().setId(resultSet.getInt(1))
                .setName(resultSet.getString(2))
                .setIsPerMonth(resultSet.getBoolean(3))
                .setIncome(resultSet.getDouble(4))
                .setUserId(resultSet.getInt(5))
                .setTaxId(resultSet.getInt(6))
                .createIncome();
    }
}
