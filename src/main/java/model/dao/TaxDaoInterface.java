package model.dao;

import model.dao.exceptions.DaoException;
import model.entities.taxes.Tax;

import java.util.List;
import java.util.Optional;

public interface TaxDaoInterface extends GenericDao<Tax> {
    boolean updateTaxByName(Tax tax) throws DaoException;
    boolean deleteByID(int id);
    void updateAllTaxes(List<Tax> taxList) throws DaoException;
    void updateAllTaxesByName(List<Tax> taxList) throws DaoException;

    Optional<Tax> selectByName(String name) throws DaoException;
}
