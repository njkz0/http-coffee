package controller;

import exception.IncorrectLogAndPassException;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("registration")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registration.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("cabinet")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/user-cabinet.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("exit")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action.equals("login")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            try {
                User user = UserService.userSignIn(login, password);
                session.setAttribute("user", user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/user-cabinet.jsp");
                dispatcher.forward(req, resp);
            } catch (IncorrectLogAndPassException e) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error-page.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (action.equals("registration")) {
            String newLogin = req.getParameter("login");
            String newPassword = req.getParameter("password");
            String newFirstName = req.getParameter("firstName");
            String newLastName = req.getParameter("lastName");
            User user = new User().builder()
                    .login(newLogin)
                    .password(newPassword)
                    .firstName(newFirstName)
                    .lastName(newLastName)
                    .build();
            User newUser = UserService.registrationNewUser(user);
            if (newUser != null) {
                session.setAttribute("user", newUser);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error-page.jsp");
                dispatcher.forward(req, resp);
            }


        }


    }
}

