package controller;

import controller.utils.Constants;
import controller.utils.RegEx;
import controller.utils.ViewMessages;
import model.dao.UserDaoInterface;
import model.dao.impl.UserDao;
import model.entities.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        String email = req.getParameter(Constants.EMAIL_FIELD);
        String password = req.getParameter(Constants.PASSWORD_FIELD);
        req.getSession().setAttribute("taxesResult", "");

        validation(email, password, req, resp);
        out.close();
    }

    private void checkUserInDatabase(String email, String password, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> userOptional = retrieveUserFromDatabase(email, password);

        if (userOptional.isPresent()) {
            openUserPage(userOptional.get(), req, resp);
        } else {
            showError(req, resp, ViewMessages.EMAIL_AND_PASSWORD_ERROR);
        }
    }

    private Optional<User> retrieveUserFromDatabase(String email, String password) {
        UserDaoInterface userDao = new UserDao();
        return userDao.selectByEmailPassword(email, password);
    }

    private void validation(String email, String password, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RegEx.validateEmail(email)) {
            showError(request, response, ViewMessages.CHECK_EMAIL_REG_EX_ERROR);
        }
        if (!RegEx.validatePassword(password)) {
            showError(request, response, ViewMessages.CHECK_PASSWORD_REG_EX_ERROR);
        }
        if (RegEx.validateEmail(email) && RegEx.validatePassword(password)) {
            checkUserInDatabase(email, password, request, response);
        }
    }

    private void openUserPage(User user, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = getUrl(user);
        RequestDispatcher rd = req.getRequestDispatcher(url);
        req.getSession().setAttribute(Constants.USER_ID, user.getId());
        rd.forward(req, resp);
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(Constants.LOGIN_URL);
        req.getSession().setAttribute("errorMessage", errorMessage);
        rd.include(req, resp);
    }

    private String getUrl(User user) {
        if (user.isAdmin())
            return Constants.MAIN_ADMIN_URL;
        else
            return Constants.MAIN_USER_URL;
    }
}
