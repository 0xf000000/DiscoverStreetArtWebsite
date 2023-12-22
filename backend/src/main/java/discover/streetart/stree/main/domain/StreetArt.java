package discover.streetart.stree.main.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "StreetArt")
public class StreetArt {
    @Id
    private Long streetArtid;
    private Float Geolocation;
    private String base64_picture;
    private String creationDate;
    private String artist;
    private String description;
    private Timestamp date;


}
