package discover.streetart.main.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegestrationDto {

    public UserRegestrationDto(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;

    }

    private String username;
    private String email;
    private String password;

}
