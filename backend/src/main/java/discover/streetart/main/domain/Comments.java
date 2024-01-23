package discover.streetart.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Getter
@Setter

public class Comments {

    // CONSTRUCTORS
    public Comments( String comment, String user, StreetArt streetArt, Timestamp date){
        this.comment = comment;
        this.User = user;
        this.streetArt = streetArt;
        this.date = date;
    }

    // DATA VALUES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    // we need a extern key so we can order the comments to the StreetArt

    private String comment;
    //User that wrote the commment
    private String User;



    // date on which the comment got written
    private Timestamp date;

    // foreign key to StreetArt Entity
    @NonNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "streetArtid")
    private StreetArt streetArt;

}
