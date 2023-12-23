package discover.streetart.stree.main.controller;


import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.StreetArtRepositery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StreetArtController {
    private final StreetArtRepositery repositery;

    public StreetArtController(StreetArtRepositery streetArtRepositery) {
        this.repositery = streetArtRepositery;
    }

    @GetMapping("/streetArt/all")
    List<StreetArt> all(){
        return (List<StreetArt>) repositery.findAll();
    }

    


    //Get

    //Put

    //DELETE

    //Post

}
