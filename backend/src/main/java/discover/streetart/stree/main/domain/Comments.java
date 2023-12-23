package discover.streetart.stree.main.domain;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comments {

    // CONSTRUCTORS
    public Comments( String comment, String user, StreetArt streetArt, Timestamp date){
        this.comment = comment;
        this.User = user;
        this.steetArt = streetArt;
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

    // foreign key to StreetArt Entity
    @NonNull
    @ManyToOne()
    @JoinColumn(name = "streetArtid")
    private StreetArt steetArt;

    // date on which the comment got written
    private Timestamp date;

}
