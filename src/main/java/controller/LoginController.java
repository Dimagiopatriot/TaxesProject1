package controller;

import model.dao.GenericDao;
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
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPass");

        UserDaoInterface userDao = new UserDao();
        Optional<User> userOptional = userDao.selectByEmailPassword(email, password);

        if (userOptional.isPresent()) {
            RequestDispatcher rd = req.getRequestDispatcher("main_user.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            req.getSession().setAttribute("errorMessage", "Невірно вказаний логін або пароль!");
            rd.include(req, resp);
        }
        out.close();
    }
}
