package model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
  //  private int id;
    @OneToOne( targetEntity = User.class)
    private User user;

    @Column (name = "time")
    private String time;

    public Cart(User user) {
        this.user=user;
        time= new Date().toString();
    }
}
