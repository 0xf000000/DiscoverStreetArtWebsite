package discover.streetart.main.service;

import discover.streetart.main.domain.Comments;
import discover.streetart.main.domain.StreetArt;
import discover.streetart.main.repositery.CommentRepositery;
import discover.streetart.main.repositery.StreetArtRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StreetArtService {
    @Autowired
    private StreetArtRepositery streetArtRepositery;

    @Autowired
    private CommentRepositery commentRepositery;

    public List<Comments> getCommentsByStreetArtId(Long streetArtId){
        StreetArt streetArt = streetArtRepositery.findById(streetArtId).orElseThrow(() -> new RuntimeException("StreetArtDataEntityNotFound"));

        return streetArt.getComments();
    }

    public StreetArt saveStreetARt( StreetArt streetArt){
         return streetArtRepositery.save(streetArt);
    }

    public void deleteStreetArt(Long id){

        streetArtRepositery.deleteById(id);
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
