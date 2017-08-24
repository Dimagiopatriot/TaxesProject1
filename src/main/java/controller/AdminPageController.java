package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageController extends HttpServlet {

    private final static String WORK_TAX_NAME = "work";
    private final static String REWARD_TAX_NAME = "reward";
    private final static String PROPERTY_TAX_NAME = "property";
    private final static String GIFTS_TAX_NAME = "gifts";
    private final static String TRANSFER_TAX_NAME = "transfer";
    private final static String CHILDREN_PRIVILEGES_TAX_NAME = "children_privileges";
    private final static String MATERIAL_AID_TAX_NAME = "material_aid";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
