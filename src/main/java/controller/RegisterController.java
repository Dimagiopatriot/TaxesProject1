package controller;

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
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPass");

        User registeredUser = new User(email, password);
        UserDao userDao = new UserDao();
        boolean result = userDao.insert(registeredUser);
        if (result) {
            out.print("Ви зареєстровані!!");
            out.print("<br/><br/><a href=\"main_guest.jsp\">Перейти на головну</a>");
        } else {
            out.print("На жаль, виникла помилка. Спробуйте ще раз.");
            out.print("<br/><br/><a href=\"main_guest.jsp\">Перейти на головну</a>");
        }
        out.close();
    }
}

