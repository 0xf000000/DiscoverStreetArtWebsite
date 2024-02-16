package discover.streetart.main.service;

import discover.streetart.main.domain.Comments;
import discover.streetart.main.domain.StreetArt;
import discover.streetart.main.repositery.CommentRepositery;
import discover.streetart.main.repositery.StreetArtRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public List<StreetArt> getLatestStreetArt(){
         ArrayList<StreetArt> ArtList = (ArrayList<StreetArt>) streetArtRepositery.findAll();
         if(ArtList.size() <= 6){
             return ArtList;
         }

               // using here var operator cause it should be clear what datatype were referenzing
        Collections.sort(ArtList);
        ArrayList<StreetArt> finalArtList = new ArrayList<StreetArt>();
        for( int i = ArtList.size() -1; i > ArtList.size() - 6; i--){
            finalArtList.add(ArtList.get(i));
        }

        return finalArtList;
    }


    public Optional<StreetArt> findById(Long id){

        return streetArtRepositery.findById(id);

    }



    public List<StreetArt> getAll(){

        return  (List<StreetArt>) streetArtRepositery.findAll();
    }

}
