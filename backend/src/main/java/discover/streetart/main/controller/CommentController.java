package discover.streetart.main.controller;


import discover.streetart.main.domain.Comments;
import discover.streetart.main.service.CommentService;
import discover.streetart.main.service.StreetArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private StreetArtService streetArtService;

    // get comments corresponding to the wanted streetArtLocationPoint

    @GetMapping(value = "/comments/{id}", produces = "application/json;utf-8")
    public ResponseEntity<List<Comments>> getCommentsByStreetArtID( @PathVariable Long id  ){
        ResponseEntity responseEntity = null;
        List<Comments> CommentList =   streetArtService.getCommentsByStreetArtId(id);

     if(CommentList.isEmpty()){
         responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

     } else{

         responseEntity = new ResponseEntity<>(CommentList, HttpStatus.OK);
     }

     return responseEntity;
    }


    @PostMapping(value = "/comments", consumes = "application/json;utf-8")
    public ResponseEntity<String> postComment(@RequestBody Comments comments ){


            commentService.saveComment(comments);




        return new ResponseEntity<>("comment Saved", HttpStatus.OK);

    }

    // PUT endpoint


    //DELETE COMMENT ENDPOINT
    @DeleteMapping("/comments/delete/{id}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long id){

        commentService.deleteComment(id);



        return new ResponseEntity<>( id, HttpStatus.OK);
    }



}
