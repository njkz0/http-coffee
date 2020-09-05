import controller.dto.CartItemDTO;
import dao.CartItemDAO;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

     ArrayList<CartItemDTO> cartItemDTOs= CartItemDAO.getCartItemDTOByCartID(8);
        System.out.println(cartItemDTOs.get(0).getName());
        System.out.println(cartItemDTOs.get(1).getName());
    }
}
