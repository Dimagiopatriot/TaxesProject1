package model.dao;

import model.dao.impl.IncomeDao;
import model.dao.impl.TaxDao;
import model.dao.impl.UserDao;

public class DaoFactory {

    private DaoFactory() {
    }

    private static class Holder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    public IncomeDaoInterface getIncomeDao(){
        return IncomeDao.getInstance();
    }

    public TaxDaoInterface getTaxDao(){
        return TaxDao.getInstance();
    }

    public UserDaoInterface getUserDao(){
        return UserDao.getInstance();
    }
}
