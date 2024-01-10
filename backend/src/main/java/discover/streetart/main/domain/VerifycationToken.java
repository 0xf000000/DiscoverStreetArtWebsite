package discover.streetart.main.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerifycationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "UserID")
    private User user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());

    }
}
