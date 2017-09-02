package controller.utils;

import model.dao.TaxDaoInterface;
import model.dao.exceptions.DaoException;
import model.dao.impl.TaxDao;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;

import java.util.Arrays;
import java.util.List;

import static controller.utils.Constants.*;

public class TaxesInit {
    private static TaxesInit ourInstance;

    public static void createDefaultTaxesInDatabase() {
        if (ourInstance == null){

            ourInstance = new TaxesInit();
            TaxDaoInterface taxDao = new TaxDao();
            Tax work = createTax(WORK_TAX_NAME);
            Tax reward = createTax(REWARD_TAX_NAME);
            Tax property = createTax(PROPERTY_TAX_NAME);
            Tax gifts = createTax(GIFTS_TAX_NAME);
            Tax transfer = createTax(TRANSFER_TAX_NAME);
            Tax childrenPrivileges = createTax(CHILDREN_PRIVILEGES_TAX_NAME);
            Tax materialAid = createTax(MATERIAL_AID_TAX_NAME);
            List<Tax> taxes = Arrays.asList(work, reward, property, gifts, transfer, childrenPrivileges, materialAid);

            for (Tax tax : taxes) {
                try {
                    updateTaxInDatabase(tax, taxDao);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private TaxesInit() {
    }

    private static Tax createTax(String type) {
        return new TaxBuilder()
                .setName(type)
                .setTaxPercent(0.)
                .createTax();
    }

    private static void updateTaxInDatabase(Tax tax, TaxDaoInterface taxDao) throws DaoException {
        if (!taxDao.updateTaxByName(tax)){ insertTaxInDatabase(tax, taxDao);}
    }

    private static void insertTaxInDatabase(Tax tax, TaxDaoInterface taxDao) throws DaoException {
        taxDao.insert(tax);
    }
}
