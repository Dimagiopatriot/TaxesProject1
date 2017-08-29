package controller;

import controller.utils.Constants;
import controller.utils.ViewMessages;
import model.dao.UserDaoInterface;
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

        boolean result = saveUserToDatabase(email, password);
        if (result) {
            showCongratulation(out);
        } else {
            showError(out);
        }
        out.close();
    }

    private boolean saveUserToDatabase(String email, String password){
        User registeredUser = new User(email, password);
        UserDaoInterface userDao = new UserDao();
        return userDao.insert(registeredUser);
    }

    private void showCongratulation(PrintWriter out){
        out.print(ViewMessages.REGISTER_CONGRATULATION);
        out.print("<br/><br/><a href=\"" + Constants.MAIN_GUEST_URL + "\">Перейти на головну</a>");
    }

    private void showError(PrintWriter out){
        out.print(ViewMessages.REGISTER_ERROR);
        out.print("<br/><br/><a href=\""+ Constants.MAIN_GUEST_URL + "\">Перейти на головну</a>");
    }
}

