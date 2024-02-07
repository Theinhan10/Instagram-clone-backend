package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Users;
import com.project.instagramclonebackend.Repository.UserRepo;
import com.project.instagramclonebackend.exception.NoSuchUserExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service handle the business logic for submitting our data into the database
//Business logic is responsible for implementing the core functionality and rules of an application.
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    //adding the user
    public Users addUser(Users user){
        return userRepo.save(user);
    }

    //getting the user
    public Users getUser(Integer userId) {
        return userRepo.findById(userId).orElseThrow(
                ()->new NoSuchUserExistsException("No User was present with ID " + userId));
    }

    public List<Users> getAllUsers(){
        return (List<Users>) userRepo.findAll(); //(List<Users>) syntax tell compiler to expect result to be a List comtaining elements of users class
    }


    //deleting users
    public String deleteUser(Integer userId) {
        if(!userRepo.existsById(userId)){
            throw new NoSuchUserExistsException("Unable to delete. No User was present with ID " + userId);
        }
        userRepo.deleteById(userId);
        return "User with id " + userId + " has been deleted success.";
    }

    //Updating user
    public Users updateUser(Users newUser, Integer userId){
        return userRepo.findById(userId).map(
                user->{
                    user.setUserName(newUser.getUserName());
                    user.setName(newUser.getName());
                    user.setProfileImage(newUser.getProfileImage());
                    user.setUserId(newUser.getUserId());
                    return userRepo.save(user);
                }
        ).orElseThrow(()-> new NoSuchUserExistsException("Unable to find the users to update! " + userId));
    }






}
