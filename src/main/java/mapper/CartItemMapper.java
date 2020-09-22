package mapper;

import controller.dto.CartItemDTO;
import model.CartItem;
import service.CartItemService;

import java.util.ArrayList;
import java.util.List;

public class CartItemMapper {

    public static List getCartItemsDTO(int userID) {
        List<CartItem> cartItems = CartItemService.getAllItemsInCart(userID);
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO;
        if (cartItems != null) {
            for (int i = 0; i < cartItems.size(); i++) {
                cartItemDTO = new CartItemDTO(cartItems.get(i).getItem().getName(), cartItems.get(i).getAmount(), cartItems.get(i).getItem().getPrice());
                cartItemDTOs.add(cartItemDTO);
            }
            return cartItemDTOs;
        }
        return null;
    }

}
