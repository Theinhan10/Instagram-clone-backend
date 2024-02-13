package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
