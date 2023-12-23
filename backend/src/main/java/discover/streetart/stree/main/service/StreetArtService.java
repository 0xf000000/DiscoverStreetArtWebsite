package discover.streetart.stree.main.service;

import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.StreetArtRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StreetArtService {
    @Autowired
    private StreetArtRepositery streetArtRepositery;

    public StreetArt saveStreetARt( StreetArt streetArt){
         return streetArtRepositery.save(streetArt);
    }


    public Optional<StreetArt> findById(Long id){

        return streetArtRepositery.findById(id);

    }

    public void AddStreetArt(StreetArt streetArt){
        streetArtRepositery.save(streetArt);
    }


    public List<StreetArt> getAll(){

        return  (List<StreetArt>) streetArtRepositery.findAll();
    }

}
