package discover.streetart.stree.main.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class comments {

    @Id
    private Long ID;
    // we need a extern key so we can order the comments to the StreetArt

    private String comment;
    //User that wrote the commment
    private String User;

    // foreign key to StreetArt Entity
    @ManyToOne()
    @JoinColumn(name = "streetArtid")
    private StreetArt steetArt;

    // date on which the comment got written
    private Timestamp date;

}
