package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Image;
import com.project.instagramclonebackend.Entity.Users;
import com.project.instagramclonebackend.Entity.PostImage;
import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Repository.ImageRepo;
import com.project.instagramclonebackend.Repository.PostImageRepo;
import com.project.instagramclonebackend.Repository.PostRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ImageRepo imageRepo;

    @Autowired
    UserService userService;

    @Autowired
    PostImageRepo postImageRepo;

/**
    public Post addPost(Post post){
        try{

            // Fetch user by unique ID
            Users user = userService.getUserByUniqueId(post.getUserUID());

            post.setUserName(userService.getUserByUniqueId(post.getUserUID()).getUserName());

            // Establish relationship between Post and Users
           // post.setUser(user);
            // Save the post
            Post savedPost = postRepo.save(post);

            // Retrieve the list of image URLs associated with the post
            List<String> imageUrls = post.getImageUrls();
            System.out.println("Image URLs: " + imageUrls); // Debugging


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
 **/

public Post addPost(Post post) {
    try {
        // Fetch user by unique ID
        Users user = userService.getUserByUniqueId(post.getUserUID());

        // Establish relationship between Post and Users
        post.setUser(user);

        // Set the userName of the post based on the user UID
        post.setUserName(user.getUserName());

        // Save the post
        Post savedPost = postRepo.save(post);

        // Retrieve the list of image URLs associated with the post
        List<String> imageUrls = post.getImageUrls();

        // Check if the list of image URLs is not null and not empty
        if (imageUrls != null && !imageUrls.isEmpty()) {
            // Initialize a new list to store Image entities
            List<PostImage> images = new ArrayList<>();

            // Iterate through each image URL
            for (String imageUrl : imageUrls) {
                // Create a new Image entity
                PostImage postImage = new PostImage();
                // Establish the relationship between post and postImage
                postImage.setPost(savedPost);
                // Set the imagePath for the image entity to the current image URL
                postImage.setImageUrl(imageUrl);
                // Add the created Image entity to the list of PostImages entity
                images.add(postImage);
            }

            // Save the list of images
            postImageRepo.saveAll(images);

            // Update the savedPost entity with the list of saved images
            savedPost.setImages(images);
            // Save the updated post
            postRepo.save(savedPost);
        }

        // Update the list of posts in the user entity
        List<Post> userPosts = user.getPosts();
        if (userPosts == null) {
            userPosts = new ArrayList<>();
        }
        userPosts.add(savedPost);
        user.setPosts(userPosts);

        // Save the user entity to update the list of posts
        userService.updateUser(user, user.getUserId());

        return savedPost;
    } catch (Exception e) {
        // Handle exception or log an error message
        throw new NoSuchExistsException("Unable to add post: " + e.getMessage(), e);
    }
}



    public Post getPost(Long postId) {
        return postRepo.findById(postId).orElseThrow(
                ()->new NoSuchExistsException("No Post was present with ID " + postId));
    }

    public List<Post> getAllPosts(){
        List<Post> postList = postRepo.findAll();
        postList.sort(Comparator.comparingLong(Post::getPostId).reversed());
        return postList;
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
                    post.setTimestamp(newPost.getTimestamp());
                    post.setLikeCount(newPost.getLikeCount());
                    post.setUserUID(newPost.getUserUID());
                    post.setCaption(newPost.getCaption());
                    return postRepo.save(post);
               }
       ).orElseThrow(()-> new NoSuchExistsException("Unable to find the Post to update! " + postId));
    }

}
