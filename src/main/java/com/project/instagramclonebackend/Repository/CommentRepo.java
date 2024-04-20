package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository< Comment, Long> {


}
