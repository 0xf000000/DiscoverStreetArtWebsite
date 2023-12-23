package discover.streetart.stree.main.service;


import discover.streetart.stree.main.domain.Comments;
import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.CommentRepositery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService  {

    @Autowired
    CommentRepositery commentRepositery;
    public List<Comments> getCommentsFromStreetArtPoint(Long id){

        return (List<Comments>) commentRepositery.getCommentsFromStreetArt(id);

    }




    public void addComment(Comments comment){

        commentRepositery.save(comment);

    }



    

}
