package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository <Status, Long> {

}
