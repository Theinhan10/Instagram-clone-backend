package com.project.instagramclonebackend.Repository;

import com.project.instagramclonebackend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//a Spring annotation that indicates that the annotated class is a repository, responsible for encapsulating interaction with a data source (such as a database).
@Repository
public interface UserRepo extends JpaRepository<Users, Long> { //CrudRepository interface is a generic interface provided by Spring Data that defines CRUD (Create, Read, Update, Delete) operations for entities.
    //these are customize method in case CrudRepository don't provide a method I need
    //Users save(Users user);
   Optional<Users> findByUserUniqueId(String userUniqueId);

    //Users deleteByUserId(String userid);
}
