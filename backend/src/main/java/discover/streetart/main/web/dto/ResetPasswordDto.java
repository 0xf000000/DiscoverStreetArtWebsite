package discover.streetart.main.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordDto {
    String password;
    String token;

    public ResetPasswordDto(String password, String token){
        this.password = password;
        this.token = token;

    }
}
