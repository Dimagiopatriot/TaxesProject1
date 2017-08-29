package controller.utils;

import model.dao.TaxDaoInterface;
import model.dao.impl.TaxDao;
import model.entities.taxes.Tax;

import java.util.Optional;

public class TaxRetrieve {

    private TaxDaoInterface taxDao = new TaxDao();

    public double retrieveTaxPercentFromDatabase(String taxType) {
        return checkTaxPercent(taxDao.selectByName(taxType));
    }

    public int retrieveTaxIdFromDatabase(String taxType) {
        Optional<Tax> tax = taxDao.selectByName(taxType);
        if (tax.isPresent())
            return tax.get().getId();
        else
            return 0;
    }

    private double checkTaxPercent(Optional<Tax> tax) {
        if (tax.isPresent()) {
            return tax.get().getTaxPercent();
        }
        return 0.;
    }
}
