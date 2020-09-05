package service;

import controller.dto.CartItemDTO;
import dao.CartDAO;
import dao.CartItemDAO;
import model.Cart;
import model.CartItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CartItemService {

    public static ArrayList getAllCartItems(int userID) {
        Cart cart = CartDAO.searchCartByUserID(userID);
        if (cart != null) {
            ArrayList<CartItem> cartItems = CartItemDAO.searchAllCartItemsByCartID(cart.getId());
            return cartItems;
        } else return null;
    }

    public static void buy(int userID) {
        ArrayList<CartItem> cartItems = getAllCartItems(userID);
        for (int i = 0; i < cartItems.size(); i++) {
            CartItemDAO.deleteCartItem(cartItems.get(i).getId());
        }
        Cart cart = CartDAO.searchCartByUserID(userID);
        CartDAO.deleteCart(cart.getId());
    }

    public static ArrayList getAllItemsInCart(int userID) {
        ArrayList<CartItem> cartItems = getAllCartItems(userID);
        if (cartItems != null) {
            ArrayList<Integer> idOfItems = new ArrayList<>();
            for (int i = 0; i < cartItems.size(); i++) {
                idOfItems.add(cartItems.get(i).getItemID());
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
                    if (idOfItems.get(i) == cartItems.get(j).getItemID()) {
                        amount += cartItems.get(j).getAmount();
                    }
                }
                CartItem cartItem = new CartItem(idOfItems.get(i), cartItems.get(0).getCartID(), amount);
                for (int n = 0; n < cartItems.size(); n++) {
                    if (cartItems.get(n).getItemID() == idOfItems.get(i)) {
                        middle.add(cartItems.get(n));
                    }
                }
                for (int n = 0; n < middle.size() - 1; n++) {
                    CartItemDAO.deleteCartItem(middle.get(n).getId());
                }
                cartItem = CartItemDAO.updateCartItem(cartItem, middle.get(middle.size() - 1).getId());
                middle.clear();
                result.add(cartItem);
            }

            return result;
        } else return null;
    }

    public static ArrayList getCartItemsDTO(int userID) {
        getAllItemsInCart(userID);
        Cart cart = CartDAO.searchCartByUserID(userID);
        if (cart != null) {
            ArrayList<CartItemDTO> cartItemDTOs = CartItemDAO.getCartItemDTOByCartID(cart.getId());
            return cartItemDTOs;
        } else return null;
    }

    public static int getTotalPrice(ArrayList<CartItemDTO> cartItemDTOS) {
        int total = 0;
        for (int i = 0; i < cartItemDTOS.size(); i++) {
            total += cartItemDTOS.get(i).getFullPrice();
        }
        return total;
    }
}
