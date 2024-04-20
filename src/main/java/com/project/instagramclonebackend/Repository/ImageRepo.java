package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository <Image, Long> {

}
