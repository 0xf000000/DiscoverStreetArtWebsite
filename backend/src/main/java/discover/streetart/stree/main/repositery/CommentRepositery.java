package discover.streetart.stree.main.repositery;

import discover.streetart.stree.main.domain.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepositery extends CrudRepository<Comments, Long> {

}
