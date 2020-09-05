package model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private int price;
}
