package discover.streetart.stree.main.controller;


import discover.streetart.stree.main.domain.Comments;
import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.service.CommentService;
import discover.streetart.stree.main.service.StreetArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private StreetArtService streetArtService;

    // get comments corresponding to the wanted streetArtLocationPoint

    @GetMapping(value = "/{id}")
    public List<Comments> getCommentsByStreetArtID( @PathVariable Long id  ){

     List<Comments> CommentList =   streetArtService.getCommentsByStreetArtId(id);

     return CommentList;

    }



}
