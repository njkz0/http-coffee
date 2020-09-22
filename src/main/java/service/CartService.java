package service;

import dao.CartDAO;
import dao.UserDAO;
import model.Cart;
import model.User;

public class CartService {

    private static UserDAO userDAO = UserDAO.getUserDAO();
    private static CartDAO cartDAO = CartDAO.getCartDAO();

    public static Cart createNewCartIfNotExist(User user)  {
        if (userDAO.searchSuchUser(user.getLogin()) != null) {
            Cart result = cartDAO.searchCartByUserID(user.getId());
            if (result == null) {
                result = new Cart(user);
                return cartDAO.save(result);
            } else return result;
        } else return null;
    }
}
