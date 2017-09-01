package controller;

import controller.utils.Constants;
import controller.utils.ViewMessages;
import model.dao.UserDaoInterface;
import model.dao.exceptions.DaoException;
import model.dao.impl.UserDao;
import model.entities.users.User;

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

        boolean result = false;
        if (!isUserRegistered(email, password)){
            result = registerUser(email, password);
        } else {
            showMessage(out, ViewMessages.USER_REGISTERED_BEFORE_ERROR, Constants.MAIN_GUEST_URL);
        }
        if (result) {
            showMessage(out, ViewMessages.REGISTER_CONGRATULATION, Constants.MAIN_USER_URL);
        } else {
            showMessage(out, ViewMessages.REGISTER_ERROR, Constants.MAIN_GUEST_URL);
        }
        out.close();
    }

    private boolean saveUserToDatabase(String email, String password) throws DaoException {
        User registeredUser = new User(email, password);
        UserDaoInterface userDao = new UserDao();
        return userDao.insert(registeredUser);
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

    private void showMessage(PrintWriter out, String msg, String url){
        out.print(msg);
        out.print("<br/><br/><a href=\"" + url + "\">Перейти на головну</a>");
    }
}

