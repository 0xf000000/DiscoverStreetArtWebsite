package discover.streetart.main.service;


import discover.streetart.main.domain.Comments;
import discover.streetart.main.repositery.CommentRepositery;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CommentService  {

    @Autowired
    CommentRepositery commentRepositery;


    public void deleteComment(Long id){
        commentRepositery.deleteById(id);
    }

    public void saveComment( Comments comments){
        commentRepositery.save(comments);
    }




    public void addComment(Comments comment){

        commentRepositery.save(comment);

    }



    

}
