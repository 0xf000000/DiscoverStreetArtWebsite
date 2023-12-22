package discover.streetart.stree.main.service;

import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.StreetArtRepositery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StreetArtService {
    @Autowired
    private StreetArtRepositery streetArtRepositery;


    public Optional<StreetArt> findById(Long id){

        return streetArtRepositery.findById(id);

    }


    public List<StreetArt> getAll(){

        return  (List<StreetArt>) streetArtRepositery.findAll();
    }

}
