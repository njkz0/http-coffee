package service;

import dao.BaseDAO;
import dao.CartItemDAO;
import dao.ItemDAO;
import dao.UserDAO;
import exception.IncorrectLogAndPassException;
import model.Cart;
import model.CartItem;
import model.Item;
import model.User;

public class UserService {

    private static UserDAO userDAO = UserDAO.getUserDAO();

    public static User registrationNewUser(User user) {

        if (userDAO.searchSuchUser(user.getLogin()) == null) {
            return userDAO.save(user);
        } else {
            return null;
        }
    }

    public static User userSignIn(String login, String password) throws IncorrectLogAndPassException {
        User user = userDAO.searchSuchUser(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;

        } else {
            throw new IncorrectLogAndPassException("Login and Password incorrect");

        }
    }



}
