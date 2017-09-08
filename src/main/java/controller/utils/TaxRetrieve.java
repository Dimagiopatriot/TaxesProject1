package controller.utils;

import model.dao.exceptions.DaoException;
import model.entities.taxes.Tax;
import model.service.TaxService;

import java.util.Optional;

public class TaxRetrieve {

    private TaxService service = TaxService.getInstance();

    public double retrieveTaxPercentFromDatabase(String taxType)  {
        try {
            return checkTaxPercent(service.selectByName(taxType));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int retrieveTaxIdFromDatabase(String taxType) {
        Optional<Tax> tax = Optional.empty();
        try {
            tax = service.selectByName(taxType);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return tax.map(Tax::getId).orElse(0);
    }

    private double checkTaxPercent(Optional<Tax> tax) {
        return tax.map(Tax::getTaxPercent).orElse(0.);
    }
}
