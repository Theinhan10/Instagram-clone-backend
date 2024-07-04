package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Entity.PostImage;
import com.project.instagramclonebackend.Service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PostImage")
@CrossOrigin
public class PostImageController {

    @Autowired
    PostImageService imageService;

    @PostMapping("")
    private PostImage addPostImage(@RequestBody PostImage postImage) {
        return imageService.addPostImage(postImage);
    }

}
