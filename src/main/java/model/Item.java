package model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    //private int id;
    @Column
    private String name;
    @Column
    private Integer price;
}
