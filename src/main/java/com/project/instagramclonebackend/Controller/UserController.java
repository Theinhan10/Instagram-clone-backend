package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Users;
import com.project.instagramclonebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    //annotation tells the Spring framework to automatically inject the required dependency (UserService in this case) at runtime.
    @Autowired
    UserService userService;

    @PostMapping("") //anything that end with /user will go with this postmapping method.
    private Users addUser(@RequestBody Users users){
        return userService.addUser(users);
    }


    @GetMapping("/{userid}") // @PathVariable is an annotation used in a Spring MVC controller to extract values from the URI path.
    private Users getUser(@PathVariable("userid") Long userId){
        return userService.getUser(userId);
    }

    @GetMapping("/allUsers")
    List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser/{userId}")
    private Users updateUser(@RequestBody Users newUser, @PathVariable("userId") Long userId){
        return userService.updateUser(newUser, userId);
    }

    @DeleteMapping("/deleteUser/{userid}")
    public String deleteUser(@PathVariable Long userid){
        return userService.deleteUser(userid);
    }


    @GetMapping("userUniqueId/{userUniqueId}")
    private Users getUserByUniqueId (@PathVariable("userUniqueId") String userUniqueId){
        return userService.getUserByUniqueId(userUniqueId);
    }






}
