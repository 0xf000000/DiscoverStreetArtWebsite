package discover.streetart.main.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "StreetArt")
@Getter
@Setter
@NoArgsConstructor
public class StreetArt implements Comparable<StreetArt> {

    // CONSTRUCTORS
    public StreetArt( Double Longitude, Double Latitude, String pictureName, String picturePointer, String creationDate, String artist, String desc, String date ){
        this.artist = artist;
        this.picturePointer = picturePointer;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.description = desc;
        this.picture_Name = pictureName;
        this.date = date;
        this.creationDate = creationDate;
    }




    // DATA FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetArtId;
    private Double  Latitude;

    private Double Longitude;
    private String picture_Name;

    // we will use some kind of pointer here not storing the raw picture in the DB
    private String picturePointer;
    private String creationDate;
    private String artist;
    private String description;
    private String date;

    // we need to save which user posted which streetArtPoint ?? do we?


    @OneToMany(mappedBy = "streetArt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();


    // cause we need to compare fucking dates in orde to get the 6 latest dates
    @Override
    public int compareTo(StreetArt o) {
        if(o.getDate() == null || this.getDate() == null){
            return 0;
        }
         Date date1 =  java.sql.Date.valueOf(o.getDate());
         Date date2 = java.sql.Date.valueOf(getDate());



        return   date2.compareTo(date1);
    }
}
