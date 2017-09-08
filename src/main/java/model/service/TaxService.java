package model.service;

import model.dao.DaoFactory;
import model.dao.exceptions.DaoException;
import model.entities.taxes.Tax;

import java.util.List;
import java.util.Optional;

public class TaxService {

    private DaoFactory daoFactory;

    TaxService(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    private static class Holder{
        static final TaxService INSTANCE = new TaxService(DaoFactory.getInstance());
    }

    public static TaxService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean delete(int id) {
        boolean deleted;
        deleted = daoFactory.getTaxDao().deleteByID(id);
        return deleted;
    }

    public boolean update(Tax tax) throws DaoException {
        boolean updated;
        updated = daoFactory.getTaxDao().update(tax);
        return updated;
    }

    public boolean updateTaxByName(Tax tax) throws DaoException {
        boolean updated;
        updated = daoFactory.getTaxDao().updateTaxByName(tax);
        return updated;
    }

    public void updateAllTaxes(List<Tax> taxList) throws DaoException {
        daoFactory.getTaxDao().updateAllTaxes(taxList);
    }

    public void updateAllTaxesByName(List<Tax> taxList) throws DaoException {
        daoFactory.getTaxDao().updateAllTaxesByName(taxList);
    }

    public boolean insert(Tax tax) throws DaoException {
        boolean inserted;
        inserted = daoFactory.getTaxDao().insert(tax);
        return inserted;
    }

    public Optional<Tax> select(int id) throws DaoException {
        Optional<Tax> tax;
        tax = daoFactory.getTaxDao().select(id);
        return tax;
    }

    public Optional<Tax> selectByName(String name) throws DaoException {
        Optional<Tax> tax;
        tax = daoFactory.getTaxDao().selectByName(name);
        return tax;
    }

    public void selectTaxIfExist(Tax tax) throws DaoException {
        if (!selectByName(tax.getName()).isPresent()){
            insert(tax);
        }
    }

    public void updateTaxIfExist(Tax tax) throws DaoException {
        if(!updateTaxByName(tax)){
            insert(tax);
        }
    }

    public void updateTaxListIfExist(List<Tax> taxes) throws DaoException {
        for (Tax tax : taxes){
            updateTaxIfExist(tax);
        }
    }
}
