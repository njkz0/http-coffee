package service;

import dao.CartItemDAO;
import dao.UserDAO;
import exception.IncorrectLogAndPassException;
import model.Cart;
import model.CartItem;
import model.User;

public class UserService {
    public static User registrationNewUser(User user) {
        if (UserDAO.searchSuchUser(user.getLogin()) == null) {
            return UserDAO.saveUser(user);
        } else {
            return null;
        }
    }

    public static User userSignIn(String login, String password) throws IncorrectLogAndPassException {
        User user = UserDAO.searchSuchUser(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;

        } else {
            throw new IncorrectLogAndPassException("Login and Password incorrect");

        }
    }

    public static CartItem addCartItemToCart(String login, int itemID, int amount) {
            Cart cart = CartService.createNewCartIfNotExist(UserDAO.searchSuchUser(login));
            CartItem cartItem = new CartItem(itemID,cart.getId() , amount);
            return CartItemDAO.saveCartItem(cartItem);
    }


}
