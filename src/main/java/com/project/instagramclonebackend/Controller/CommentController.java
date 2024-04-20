package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Comment;
import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping("")
    private Comment addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }


    @GetMapping("/{commentId}") // @PathVariable is an annotation used in a Spring MVC controller to extract values from the URI path.
    private Comment getComment(@PathVariable("commentId") Long commentId){
        return commentService.getComment(commentId);
    }

    @GetMapping("/allComments")
    List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @PutMapping("/updateComment/{commentId}")
    private Comment updateComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId){
        return commentService.updateComment(comment, commentId);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
    
    
}
