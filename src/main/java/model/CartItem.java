package model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartItem {
    private int id;
    private int itemID;
    private int cartID;
    private int amount;

   public CartItem(int itemID, int cartID, int amount){
    this.cartID=  cartID;
    this.itemID= itemID;
    this.amount= amount;
   }


}
