package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Image;
import com.project.instagramclonebackend.Repository.ImageRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    public Image addImage(Image image){
        try{
            return imageRepo.save(image);
        } catch (Exception e){
            // Handle exception or log an error message
            throw new NoSuchExistsException("Unable to add image: " + e.getMessage(), e);
        }
    }
}
