package discover.streetart.stree.main.repositery;

import discover.streetart.stree.main.domain.Comments;
import discover.streetart.stree.main.domain.StreetArt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepositery extends CrudRepository<Comments, Long> {


    /**
     * gets all comments from one StreetArtPoint

     * @return
     */
    @Query("SELECT comment FROM Comments c  WHERE c.streetArt = ?1")
    public List<Comments> getCommentsFromStreetArt(StreetArt streetArt);

}
