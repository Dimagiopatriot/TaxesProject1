import controller.AdminPageController;
import model.dao.impl.TaxDao;
import model.dao.impl.UserDao;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;
import model.entities.users.User;

import java.util.Optional;

/**
 * Created by troll on 15.08.2017.
 */
public class BuildProgram {

    public static void main(String[] args) {
        TaxDao taxDao = new TaxDao();
        Tax tax = new TaxBuilder()
                .setName("work")
                .setTaxPercent(20)
                .createTax();
        System.out.print(taxDao.update(tax));
    }
}
