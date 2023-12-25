package discover.streetart.stree.main.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "StreetArt")
@Getter
@Setter
@NoArgsConstructor
public class StreetArt {

    // CONSTRUCTORS
    public StreetArt( Float geolocation, String pictureName, String base64_picture, String creationDate, String artist, String desc, Timestamp date ){
        this.artist = artist;
        this.base64_picture = base64_picture;
        this.Geolocation = geolocation;
        this.description = desc;
        this.picture_Name = pictureName;
        this.date = date;
        this.creationDate = creationDate;
    }




    // DATA FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetArtId;
    private Float Geolocation;
    private String picture_Name;
    private String base64_picture;
    private String creationDate;
    private String artist;
    private String description;
    private Timestamp date;

    @OneToMany(mappedBy = "streetArt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();


}
