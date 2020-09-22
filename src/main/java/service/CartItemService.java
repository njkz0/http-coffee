package service;

import controller.dto.CartItemDTO;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.ItemDAO;
import dao.UserDAO;
import model.Cart;
import model.CartItem;
import model.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartItemService {

    private static UserDAO userDAO = UserDAO.getUserDAO();
    private static CartItemDAO cartItemDAO = CartItemDAO.getCartItemDAO();
    private static CartDAO cartDAO = CartDAO.getCartDAO();
    private static ItemDAO itemDAO = ItemDAO.getItemDAO();

    public static List getAllCartItems(int userID) {
        Cart cart = cartDAO.searchCartByUserID(userID);
        if (cart != null) {
            List<CartItem> cartItems = cartItemDAO.searchAllCartItemsByCartID(cart.getId());
            return cartItems;
        } else return null;
    }

    public static void buy(int userID) {
        List<CartItem> cartItems = getAllCartItems(userID);
        for (int i = 0; i < cartItems.size(); i++) {
            cartItemDAO.delete(cartItems.get(i));
        }
        Cart cart = cartDAO.searchCartByUserID(userID);
        cartDAO.delete(cart);
    }

    public static ArrayList getAllItemsInCart(int userID) {
        List<CartItem> cartItems = getAllCartItems(userID);
        if (cartItems != null) {
            ArrayList<Integer> idOfItems = new ArrayList<>();
            for (int i = 0; i < cartItems.size(); i++) {
                idOfItems.add(cartItems.get(i).getItem().getId());
            }
            Set<Integer> hs = new HashSet<>();
            hs.addAll(idOfItems);
            idOfItems.clear();
            idOfItems.addAll(hs);
            ArrayList<CartItem> result = new ArrayList<>();
            ArrayList<CartItem> middle = new ArrayList<>();
            for (int i = 0; i < idOfItems.size(); i++) {
                int amount = 0;
                for (int j = 0; j < cartItems.size(); j++) {
                    if (idOfItems.get(i) == cartItems.get(j).getItem().getId()) {
                        amount += cartItems.get(j).getAmount();
                    }
                }
                Item item = itemDAO.findById(idOfItems.get(i));
                CartItem cartItem = new CartItem(item, cartItems.get(0).getCart(), amount);
                for (int n = 0; n < cartItems.size(); n++) {
                    if (cartItems.get(n).getItem().getId() == idOfItems.get(i)) {
                        middle.add(cartItems.get(n));
                    }
                }
                for (int n = 0; n < middle.size() - 1; n++) {
                    cartItemDAO.delete(middle.get(n));
                }
                cartItem.setId(middle.get(middle.size() - 1).getId());
                cartItem = cartItemDAO.update(cartItem);
                middle.clear();
                result.add(cartItem);
            }

            return result;
        } else return null;
    }


    public static int getTotalPrice(List<CartItemDTO> cartItemDTOS) {
        int total = 0;
        for (int i = 0; i < cartItemDTOS.size(); i++) {
            total += cartItemDTOS.get(i).getFullPrice();
        }
        return total;
    }

    public static CartItem addCartItemToCart(String login, int itemID, int amount) {
        Cart cart = CartService.createNewCartIfNotExist(userDAO.searchSuchUser(login));
        Item item = itemDAO.findById(itemID);
        CartItem cartItem = new CartItem(item, cart, amount);
        return cartItemDAO.save(cartItem);
    }

}
