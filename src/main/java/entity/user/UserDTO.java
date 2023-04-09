package entity.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;

}
