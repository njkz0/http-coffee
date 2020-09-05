package service;

import dao.CartDAO;
import dao.UserDAO;
import model.Cart;
import model.User;

public class CartService {


    public static Cart createNewCartIfNotExist(User user)  {
        if (UserDAO.searchSuchUser(user.getLogin()) != null) {
            Cart result = CartDAO.searchCartByUserID(user.getId());
            if (result == null) {
                result = new Cart(user);
                return CartDAO.saveCart(result);
            } else return result;
        } else return null;
    }
}
