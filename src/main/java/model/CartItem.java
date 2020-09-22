package model;


import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Builder

@Entity
@Table (name = "cart_items")
public class CartItem extends BaseEntity{
   // private int id;
    @ManyToOne (targetEntity = Item.class)
    private Item item;
   // private Integer itemID;
    @ManyToOne (targetEntity = Cart.class)
    private Cart cart;
    @Column (name = "amount")
    private Integer amount;

   public CartItem(Item item, Cart cart, int amount){
    this.cart=  cart;
    this.item= item;
    this.amount= amount;
   }


}
