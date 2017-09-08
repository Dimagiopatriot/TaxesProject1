package controller;

import controller.utils.Constants;
import controller.utils.RegEx;
import controller.utils.ViewMessages;
import model.dao.exceptions.DaoException;
import model.entities.users.User;
import model.service.UserService;

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
        UserService userService = UserService.getInstance();

        validation(userService, email, password, request, response);
        out.close();
    }

    private void validation(UserService service, String email, String password, HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
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
            checkRegistration(service, email, password, request, response);
        }
    }

    private void checkRegistration(UserService service, String email, String password, HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (!service.isUserRegistered(email, password)){
            registrationResult(registerUser(service, email, password), out);
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

    private boolean registerUser(UserService service, String email, String password){
        try {
            return service.insert(new User(email, password));
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

