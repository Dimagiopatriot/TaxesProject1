package controller.utils;

import model.dao.exceptions.DaoException;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;
import model.service.TaxService;

import java.util.Arrays;
import java.util.List;

import static controller.utils.Constants.*;

public class TaxesInit {
    private static TaxesInit ourInstance;

    public static void createDefaultTaxesInDatabase() {
        if (ourInstance == null){

            ourInstance = new TaxesInit();
            Tax work = createTax(WORK_TAX_NAME);
            Tax reward = createTax(REWARD_TAX_NAME);
            Tax property = createTax(PROPERTY_TAX_NAME);
            Tax gifts = createTax(GIFTS_TAX_NAME);
            Tax transfer = createTax(TRANSFER_TAX_NAME);
            Tax childrenPrivileges = createTax(CHILDREN_PRIVILEGES_TAX_NAME);
            Tax materialAid = createTax(MATERIAL_AID_TAX_NAME);
            List<Tax> taxes = Arrays.asList(work, reward, property, gifts, transfer, childrenPrivileges, materialAid);

            TaxService service = TaxService.getInstance();

            for (Tax tax : taxes) {
                try {
                    service.selectTaxIfExist(tax);
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
}
