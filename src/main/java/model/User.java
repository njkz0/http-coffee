package model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
