package model.service;

import model.dao.DaoFactory;
import model.dao.exceptions.DaoException;
import model.entities.incomes.Income;

import java.util.List;
import java.util.Optional;

public class IncomeService {

    private DaoFactory daoFactory;

    IncomeService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final IncomeService INSTANCE = new IncomeService(DaoFactory.getInstance());
    }

    public static IncomeService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean delete(int id) {
        boolean deleted;
        deleted = daoFactory.getIncomeDao().deleteByID(id);
        return deleted;
    }

    public boolean update(Income income) throws DaoException {
        boolean updated;
        updated = daoFactory.getIncomeDao().update(income);
        return updated;
    }

    public boolean insert(Income income) throws DaoException {
        boolean inserted;
        inserted = daoFactory.getIncomeDao().insert(income);
        return inserted;
    }

    public Optional<Income> select(int id) throws DaoException {
        Optional<Income> income;
        income = daoFactory.getIncomeDao().select(id);
        return income;
    }

    public List<Income> selectAllIncomesForUser(int userId) throws DaoException {
        List<Income> incomes;
        incomes = daoFactory.getIncomeDao().selectAllIncomesForUser(userId);
        return incomes;
    }

    public boolean updateIncomeByUserID(Income income) throws DaoException {
        boolean updated;
        updated = daoFactory.getIncomeDao().updateIncomeByUserID(income);
        return updated;
    }

    public void updateIncomeList(List<Income> incomes) throws DaoException {
        for (Income income : incomes) {
            updateIncomeIfExist(income);
        }
    }

    private void updateIncomeIfExist(Income income) throws DaoException {
        boolean updated = updateIncomeByUserID(income);
        if (!updated) {
            insert(income);
        }
    }
}
