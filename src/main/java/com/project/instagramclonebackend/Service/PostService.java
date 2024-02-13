package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Repository.PostRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    public Post addPost(Post post){
        try{
            post.setUserName(userService.getUser(post.getUserId()).getUserName());
            return postRepo.save(post);
        } catch (DataIntegrityViolationException e){
            // Handle exception or log an error message
            throw new NoSuchExistsException("Unable to add post: " + e.getMessage(), e);
        }
    }


    public Post getPost(Long postId) {
        return postRepo.findById(postId).orElseThrow(
                ()->new NoSuchExistsException("No Post was present with ID " + postId));
    }

    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }



    public String deletePost(Long postId) {
        if(!postRepo.existsById(postId)){
            throw new NoSuchExistsException("Unable to delete. No post was present with ID " + postId);
        }
        postRepo.deleteById(postId);
        return "Post with id " + postId + " has been deleted success.";
    }


    public Post updatePost(Post newPost, Long postId){
        return postRepo.findById(postId).map(
               post->{
                    post.setPath(newPost.getPath());
                    post.setTimestamp(newPost.getTimestamp());
                    post.setLikeCount(newPost.getLikeCount());
                    post.setUserId(newPost.getUserId());
                    return postRepo.save(post);
               }
       ).orElseThrow(()-> new NoSuchExistsException("Unable to find the Post to update! " + postId));
    }

}
