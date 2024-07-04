package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.PostImage;
import com.project.instagramclonebackend.Repository.PostImageRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostImageService {

    @Autowired
    PostImageRepo PostImageRepo;

    public PostImage addPostImage(PostImage postImage){
        try{
            return PostImageRepo.save(postImage);
        } catch (Exception e){
            //Handle exception or log an error message
            throw new NoSuchExistsException("Unable to add image: " + e.getMessage(), e);
        }
    }
}
