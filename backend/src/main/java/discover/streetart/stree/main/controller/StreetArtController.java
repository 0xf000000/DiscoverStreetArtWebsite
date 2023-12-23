package discover.streetart.stree.main.controller;


import discover.streetart.stree.main.customExceptions.StreetArtNotFoundException;
import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.StreetArtRepositery;
import discover.streetart.stree.main.service.StreetArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class StreetArtController {
    @Autowired
  private StreetArtService streetArtService;

    //Get
    @GetMapping( value = "/api/v1/streetArt/all", produces = "application/json;utf-8")
    List<StreetArt> getAllstreetArt(){

        return streetArtService.getAll();
    }

    @GetMapping( value = "/api/v1/streetArt/{id}", produces = "application/json;utf-8")
   StreetArt Getbyid(@PathVariable Long id){


        // error Handeling should be here
        return streetArtService.findById(id).orElseThrow(() ->  new StreetArtNotFoundException(id));
    }

    //Post
    @PostMapping( value = "api/v1/streetArt", consumes = "application/json;utf-8")
    StreetArt addStreetArt(@RequestBody StreetArt streetArt){


       return streetArtService.saveStreetARt(streetArt);
    }
    //put mapping should be maby implemented not sure rn

    //DELETE maby later

}
