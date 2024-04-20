package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Comment;
import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Repository.CommentRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserService userService;

    public Comment addComment(Comment comment){
        try{
            comment.setUserName(userService.getUser(comment.getUserId()).getUserName());
            return commentRepo.save(comment);
        } catch (Exception e){
            // Handle exception or log an error message
            throw new NoSuchExistsException("Unable to add comment: " + e.getMessage(), e);
        }
    }


    public Comment getComment(Long commentId) {
        return commentRepo.findById(commentId).orElseThrow(
                ()->new NoSuchExistsException("No Comment was present with ID " + commentId));
    }

    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }



    public String deleteComment(Long commentId) {
        if(!commentRepo.existsById(commentId)){
            throw new NoSuchExistsException("Unable to delete. No Comment was present with ID " + commentId);
        }
        commentRepo.deleteById(commentId);
        return "Post with id " + commentId + " has been deleted success.";
    }


    public Comment updateComment(Comment newComment, Long commentId){
        return commentRepo.findById(commentId).map(
                post->{
                    post.setTimestamp(newComment.getTimestamp());
                    post.setComment(newComment.getComment());
                    post.setUserId(newComment.getUserId());
                    post.setPostId(newComment.getPostId());
                    post.setUserName(newComment.getUserName());
                    return commentRepo.save(post);
                }
        ).orElseThrow(()-> new NoSuchExistsException("Unable to find the Comment to update with ID " + commentId));
    }

}
