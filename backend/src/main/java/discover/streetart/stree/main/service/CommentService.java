package discover.streetart.stree.main.service;


import discover.streetart.stree.main.domain.Comments;
import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.CommentRepositery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class CommentService  {

    @Autowired
    CommentRepositery commentRepositery;


    public void deleteComment(Long id){
        commentRepositery.deleteById(id);
    }




    public void addComment(Comments comment){

        commentRepositery.save(comment);

    }



    

}
