package discover.streetart.stree.main.domain;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "StreetArt")
public class StreetArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetArtid;
    private Float Geolocation;
    private String picture_Name;
    private String base64_picture;
    private String creationDate;
    private String artist;
    private String description;
    private Timestamp date;


}
