package controller;

import controller.utils.Constants;
import controller.utils.RegEx;
import controller.utils.ViewMessages;
import model.dao.UserDaoInterface;
import model.dao.exceptions.DaoException;
import model.dao.impl.UserDao;
import model.entities.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String email = request.getParameter(Constants.EMAIL_FIELD);
        String password = request.getParameter(Constants.PASSWORD_FIELD);

        validation(email, password, request, response);
        out.close();
    }

    private boolean saveUserToDatabase(String email, String password) throws DaoException {
        User registeredUser = new User(email, password);
        UserDaoInterface userDao = new UserDao();
        return userDao.insert(registeredUser);
    }

    private void validation(String email, String password, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RegEx.validateEmail(email)){
            RequestDispatcher rd = request.getRequestDispatcher(Constants.REGISTER_URL);
            response.getWriter().print(ViewMessages.CHECK_EMAIL_REG_EX_ERROR);
            rd.include(request, response);
        }
        if (!RegEx.validatePassword(password)) {
            RequestDispatcher rd = request.getRequestDispatcher(Constants.REGISTER_URL);
            response.getWriter().print(ViewMessages.CHECK_PASSWORD_REG_EX_ERROR);
            rd.include(request, response);
        }
        if (RegEx.validateEmail(email) && RegEx.validatePassword(password)){
            checkRegistration(email, password, request, response);
        }
    }

    private void checkRegistration(String email, String password, HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (!isUserRegistered(email, password)){
            registrationResult(registerUser(email, password), out);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher(Constants.REGISTER_URL);
            request.getSession().setAttribute("errorUserRegisterMessage", ViewMessages.USER_REGISTERED_BEFORE_ERROR);
            rd.include(request, response);
        }
    }

    private void registrationResult(boolean result, PrintWriter out){
        if (result) {

            showMessage(out, ViewMessages.REGISTER_CONGRATULATION, Constants.LOGIN_URL, "Залогінитися");
        } else {
            showMessage(out, ViewMessages.REGISTER_ERROR, Constants.MAIN_GUEST_URL, "Зайти як гість");
        }
    }

    private boolean isUserRegistered(String email, String password) {
        UserDaoInterface userDao = new UserDao();
        return userDao.selectByEmailPassword(email, password).isPresent();
    }

    private boolean registerUser(String email, String password){
        try {
            return saveUserToDatabase(email, password);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showMessage(PrintWriter out, String msg, String url, String urlName){
        out.print(msg);
        out.print("<br/><br/><a href=\"" + url + "\">"+ urlName +"</a>");
    }
}

