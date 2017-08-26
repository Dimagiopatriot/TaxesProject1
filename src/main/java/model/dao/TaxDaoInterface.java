package model.dao;

import model.entities.taxes.Tax;

import java.util.List;
import java.util.Optional;

public interface TaxDaoInterface extends GenericDao<Tax> {
    boolean updateTaxByName(Tax tax);
    void updateAllTaxes(List<Tax> taxList);
    void updateAllTaxesByName(List<Tax> taxList);

    Optional<Tax> selectByName(String name);
}
