package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Users;
import com.project.instagramclonebackend.Repository.UserRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
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
    public Users getUser(Long userId) {
        return userRepo.findById(userId).orElseThrow(
                ()->new NoSuchExistsException("No User was present with ID " + userId));
    }

    //getting user using uid
    public Users getUserByUniqueId(String userUniqueId) {
        Optional<Users> userOptional = userRepo.findByUserUniqueId(userUniqueId);
        return userOptional.orElseThrow(() ->
                new NoSuchExistsException("No User was present with Unique ID " + userUniqueId)
        );
    }


    public List<Users> getAllUsers(){
        return (List<Users>) userRepo.findAll(); //(List<Users>) syntax tell compiler to expect result to be a List comtaining elements of users class
    }


    //deleting users
    public String deleteUser(Long userId) {
        if(!userRepo.existsById(userId)){
            throw new NoSuchExistsException("Unable to delete. No User was present with ID " + userId);
        }
        userRepo.deleteById(userId);
        return "User with id " + userId + " has been deleted success.";
    }

    //Updating user
    public Users updateUser(Users newUser, Long userId){
        return userRepo.findById(userId).map(
                user->{
                    user.setUserUniqueId(newUser.getUserUniqueId());
                    user.setUserName(newUser.getUserName());
                    user.setName(newUser.getName());
                    user.setProfileImage(newUser.getProfileImage());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }
        ).orElseThrow(()-> new NoSuchExistsException("Unable to find the users to update! " + userId));
    }

    // Overloaded method to update user without creating a new user object
    public Users updateUser(Users user) {
        return userRepo.save(user);
    }

}
