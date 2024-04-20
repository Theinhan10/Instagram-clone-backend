package com.project.instagramclonebackend.Controller;


import com.project.instagramclonebackend.Entity.Image;
import com.project.instagramclonebackend.Entity.Post;
import com.project.instagramclonebackend.Repository.ImageRepo;
import com.project.instagramclonebackend.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("")
    private Image addImage(@RequestBody Image image) {
        return imageService.addImage(image);
    }

}
