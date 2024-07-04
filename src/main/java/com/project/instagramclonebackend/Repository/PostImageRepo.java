package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepo extends JpaRepository<PostImage, Long> {


}
