package controller;

import controller.utils.Constants;
import controller.utils.ResultCalculatingPresenter;
import controller.utils.TaxRetrieve;
import controller.utils.ViewMessages;
import model.dao.exceptions.DaoException;
import model.entities.incomes.Income;
import model.entities.incomes.IncomeBuilder;
import model.entities.taxes.calculator.TaxCalculator;
import model.entities.taxes.calculator.TaxCalculatorInterface;
import model.service.IncomeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static controller.utils.Constants.*;

public class RegisteredUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Income mainWork = createIncome(WORK_INCOME_NAME, WORK_TAX_NAME, req);
        Income additionalWork = createIncome(WORK_ADD_INCOME_NAME, WORK_TAX_NAME, req);
        Income reward = createIncome(REWARD_INCOME_NAME, REWARD_TAX_NAME, req);
        Income property = createIncome(PROPERTY_INCOME_NAME, PROPERTY_TAX_NAME, req);
        Income gifts = createIncome(GIFTS_INCOME_NAME, GIFTS_TAX_NAME, req);
        Income transfer = createIncome(TRANSFER_INCOME_NAME, TRANSFER_TAX_NAME, req);
        Income childrenPrivileges = createIncome(CHILDREN_PRIVILEGES_INCOME_NAME, CHILDREN_PRIVILEGES_TAX_NAME, req);
        Income materialAid = createIncome(MATERIAL_AID_INCOME_NAME, MATERIAL_AID_TAX_NAME, req);
        List<Income> userIncomes = Arrays.asList(mainWork, additionalWork, reward, property, gifts, transfer,
                childrenPrivileges, materialAid);
        IncomeService incomeService = IncomeService.getInstance();
        try {
            incomeService.updateIncomeList(userIncomes);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        TaxCalculatorInterface taxCalculator = new TaxCalculator();
        ResultCalculatingPresenter resultForView = new ResultCalculatingPresenter();
        HttpSession session = req.getSession();

        calculateTax(ViewMessages.WORK_TAX_FOR_PAYING, taxCalculator, resultForView, mainWork,
                (Double) session.getAttribute(WORK_TAX_NAME));
        calculateTax(ViewMessages.WORK_ADD_TAX_FOR_PAYING, taxCalculator, resultForView, additionalWork,
                (Double) session.getAttribute(WORK_TAX_NAME));
        calculateTax(ViewMessages.REWARD_TAX_FOR_PAYING, taxCalculator, resultForView, reward,
                (Double) session.getAttribute(REWARD_TAX_NAME));
        calculateTax(ViewMessages.PROPERTY_TAX_FOR_PAYING, taxCalculator, resultForView, property,
                (Double) session.getAttribute(PROPERTY_TAX_NAME));
        calculateTax(ViewMessages.GIFTS_TAX_FOR_PAYING, taxCalculator, resultForView, gifts,
                (Double) session.getAttribute(GIFTS_TAX_NAME));
        calculateTax(ViewMessages.TRANSFER_TAX_FOR_PAYING, taxCalculator, resultForView, transfer,
                (Double) session.getAttribute(TRANSFER_TAX_NAME));
        calculateTax(ViewMessages.CHILDREN_PRIVILEGES_TAX_FOR_PAYING, taxCalculator, resultForView, childrenPrivileges,
                (Double) session.getAttribute(CHILDREN_PRIVILEGES_TAX_NAME));
        calculateTax(ViewMessages.MATERIAL_AID_TAX_FOR_PAYING, taxCalculator, resultForView, materialAid,
                (Double) session.getAttribute(MATERIAL_AID_TAX_NAME));
        showTaxesResult(resultForView, resp, req);
    }

    private Income createIncome(String incomeType, String taxType, HttpServletRequest request) {
        return new IncomeBuilder()
                .setName(incomeType)
                .setIsPerMonth(hasPerMonth(incomeType))
                .setIncome(Double.valueOf(request.getParameter(incomeType)))
                .setTaxId(new TaxRetrieve().retrieveTaxIdFromDatabase(taxType))
                .setUserId((int) request.getSession().getAttribute(USER_ID))
                .createIncome();
    }

    private void calculateTax(String message, TaxCalculatorInterface taxCalculator,
                              ResultCalculatingPresenter resultForView, Income income, double taxPercent) {
        resultForView.addResultItem(message, taxCalculator.calculateTax(income, taxPercent));
    }

    private void showTaxesResult(ResultCalculatingPresenter resultForView, HttpServletResponse response, HttpServletRequest req)
            throws IOException, ServletException {
        Map<String, Double> sortedViewMap = resultForView.sortResultTaxMap();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        RequestDispatcher rd = req.getRequestDispatcher(Constants.MAIN_USER_URL);
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Double> itemSortedViewMap : sortedViewMap.entrySet()) {
            result.append("<br/><br/>");
            result.append(itemSortedViewMap.toString());
        }
        req.getSession().setAttribute("taxesResult", result.toString());
        rd.include(req, response);
    }

    private boolean hasPerMonth(String incomeType) {
        return incomeType.equals(WORK_INCOME_NAME) || incomeType.equals(WORK_ADD_INCOME_NAME);
    }
}
