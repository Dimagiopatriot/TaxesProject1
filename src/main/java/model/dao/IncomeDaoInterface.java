package model.dao;

import model.dao.exceptions.DaoException;
import model.entities.incomes.Income;

import java.util.List;

/**
 * Created by troll on 16.08.2017.
 */
public interface IncomeDaoInterface extends GenericDao<Income> {
    List<Income> selectAllIncomesForUser(int userId) throws DaoException;
    boolean updateIncomeByUserID(Income income) throws DaoException;
    boolean deleteByID(int id);
}
