package model.dao;

import model.entities.incomes.Income;

import java.util.List;

/**
 * Created by troll on 16.08.2017.
 */
public interface IncomeDaoInterface extends GenericDao<Income> {
    List<Income> selectAllIncomesForUser(int userId);
}
