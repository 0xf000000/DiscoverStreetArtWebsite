package discover.streetart.main.controller;


import discover.streetart.main.customExceptions.StreetArtNotFoundException;
import discover.streetart.main.domain.StreetArt;
import discover.streetart.main.service.StreetArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class StreetArtController {
    @Autowired
  private StreetArtService streetArtService;

    //Get all
    @GetMapping( value = "/api/v1/streetArt/all", produces = "application/json;utf-8")
    List<StreetArt> getAllstreetArt(){

        return streetArtService.getAll();
    }

    // GET by id

    @GetMapping( value = "/api/v1/streetArt/{id}", produces = "application/json;utf-8")
   StreetArt Getbyid(@PathVariable Long id){


        // error Handeling should be here
        return streetArtService.findById(id).orElseThrow(() ->  new StreetArtNotFoundException(id));
    }

    //Post
    @PostMapping( value = "/api/v1/streetArt", consumes = "application/json;utf-8")
    ResponseEntity<StreetArt> addStreetArt(@RequestBody StreetArt streetArt){


       return new ResponseEntity<>(streetArtService.saveStreetARt(streetArt), HttpStatus.OK);
    }
    //put mapping should be maby implemented not sure rn

    //DELETE not sure we will implement this here
    @DeleteMapping("/api/v1/art/delete/{id}")
    public ResponseEntity<Long> deleteStreetArt(@PathVariable Long id){

        streetArtService.deleteStreetArt(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
