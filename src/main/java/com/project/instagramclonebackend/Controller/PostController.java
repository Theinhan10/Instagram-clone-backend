package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("")
    private Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }


    @GetMapping("/{postId}") // @PathVariable is an annotation used in a Spring MVC controller to extract values from the URI path.
    private Post getPost(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }

    @GetMapping("/allPosts")
    List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PutMapping("/updatePost/{postId}")
    private Post updatePost(@RequestBody Post post, @PathVariable("postId") Long postId){
        return postService.updatePost(post, postId);
    }

    @DeleteMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }
}
