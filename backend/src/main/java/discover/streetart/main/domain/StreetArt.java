package discover.streetart.main.domain;


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
    public StreetArt( Float geolocation, String pictureName, String picturePointer, String creationDate, String artist, String desc, Timestamp date ){
        this.artist = artist;
        this.picturePointer = picturePointer;
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

    // we will use some kind of pointer here not storing the raw picture in the DB
    private String picturePointer;
    private String creationDate;
    private String artist;
    private String description;
    private Timestamp date;

    // we need to save which user posted which streetArtPoint ?? do we?


    @OneToMany(mappedBy = "streetArt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();


}
