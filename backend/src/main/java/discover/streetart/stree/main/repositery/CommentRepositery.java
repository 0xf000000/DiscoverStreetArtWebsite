package discover.streetart.stree.main.repositery;

import discover.streetart.stree.main.domain.Comments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepositery extends CrudRepository<Comments, Long> {


    /**
     * gets all comments from one StreetArtPoint
     * @param id
     * @return
     */
    @Query("SELECT id FROM Comments c  WHERE c.steetArt = ?1")
    public List<Comments> getCommentsFromStreetArt(Long id);

}
