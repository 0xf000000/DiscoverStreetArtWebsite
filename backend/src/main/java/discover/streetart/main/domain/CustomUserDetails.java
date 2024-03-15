package discover.streetart.main.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails extends User{

    private  String username;
    private  String email;

    private CustomUserDetails(String username, String email) {

        this.username = username;
        this.email = email;
    }




}
