package controller;

import model.dao.TaxDaoInterface;
import model.dao.impl.TaxDao;
import model.entities.taxes.Tax;
import model.entities.taxes.TaxBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.utils.Constants.*;

public class AdminPageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaxDaoInterface taxDao = new TaxDao();

        Tax work = createTax(WORK_TAX_NAME, req);
        Tax reward = createTax(REWARD_TAX_NAME, req);
        Tax property = createTax(PROPERTY_TAX_NAME, req);
        Tax gifts = createTax(GIFTS_TAX_NAME, req);
        Tax transfer = createTax(TRANSFER_TAX_NAME, req);
        Tax childrenPrivileges = createTax(CHILDREN_PRIVILEGES_TAX_NAME, req);
        Tax materialAid = createTax(MATERIAL_AID_TAX_NAME, req);

        updateTaxInDatabase(work, taxDao);
        updateTaxInDatabase(reward, taxDao);
        updateTaxInDatabase(property, taxDao);
        updateTaxInDatabase(gifts, taxDao);
        updateTaxInDatabase(transfer, taxDao);
        updateTaxInDatabase(childrenPrivileges, taxDao);
        updateTaxInDatabase(materialAid, taxDao);
        RequestDispatcher rd = req.getRequestDispatcher("main_admin.jsp");
        rd.forward(req, resp);
    }

    private Tax createTax(String type,HttpServletRequest request) {
        return new TaxBuilder()
                .setName(type)
                .setTaxPercent(Double.parseDouble(request.getParameter(type)))
                .createTax();
    }

    private void updateTaxInDatabase(Tax tax, TaxDaoInterface taxDao){
        if (!taxDao.updateTaxByName(tax)){ insertTaInDatabase(tax, taxDao);}
    }

    private void insertTaInDatabase(Tax tax, TaxDaoInterface taxDao){
        taxDao.insert(tax);
    }

}
