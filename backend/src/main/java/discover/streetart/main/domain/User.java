package discover.streetart.main.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

/**
 * user table for authentication in our Webapp
 */
@Entity
@Table(name = "User")
@NoArgsConstructor
@Getter
@Setter
public class User {


    public User(String Username, String password, String Email, String Role){
        this.username = Username;
        this.password = password;
        this.email = Email;
        this.role =  Role;

    }
    @Column(name = "UserID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;



    @NonNull
    private String username;


    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String role;

}
