package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cart {
    private int id;
    private int userID;
    private String time;

    public Cart(User user) {
        setUserID(user.getId());
        time= new Date().toString();
    }
}
