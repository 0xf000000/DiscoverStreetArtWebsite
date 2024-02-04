package discover.streetart.main.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class PasswordResetToken {

    public PasswordResetToken(User user, String token){
        this.user = user;
        this.token = token;

    }

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name ="UserID")
    private User user;


    private Date expiryDATE;

}
