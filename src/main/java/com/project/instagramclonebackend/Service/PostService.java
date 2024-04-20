package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Image;
import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Repository.ImageRepo;
import com.project.instagramclonebackend.Repository.PostRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    @Autowired
    ImageRepo imageRepo;


    public Post addPost(Post post){
        try{
            post.setUserName(userService.getUserByUniqueId(post.getUserUID()).getUserName());
            // Save the post
            Post savedPost = postRepo.save(post);

            // Retrieve the list of image URLs associated with the post
            List<String> imageUrls = post.getImages();
            // Check if the list of image URLs is not null and not empty
            if (imageUrls != null && !imageUrls.isEmpty()) {
                // Initialize a new list to store Image entities
                List<Image> images = new ArrayList<>();
                // Iterate through each image URL
                for (String imageUrl : imageUrls) {
                    // Create a new Image entity
                    Image image = new Image();
                    // Set the postId for the image entity to the postId of the saved post
                    image.setPostId(savedPost.getPostId());
                    // Set the imagePath for the image entity to the current image URL
                    image.setImagePath(imageUrl);
                    // Add the created Image entity to the list of images
                    images.add(image);
                }
                //Save the list of images
                imageRepo.saveAll(images);
            }

            return savedPost;

        } catch (Exception e){
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
                    post.setUserUID(newPost.getUserUID());
                    return postRepo.save(post);
               }
       ).orElseThrow(()-> new NoSuchExistsException("Unable to find the Post to update! " + postId));
    }

}
