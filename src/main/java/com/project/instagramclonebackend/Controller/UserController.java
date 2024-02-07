package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Users;
import com.project.instagramclonebackend.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //annotation tells the Spring framework to automatically inject the required dependency (UserService in this case) at runtime.
    @Autowired
    UserService userService;

    @PostMapping("") //anything that end with /user will go with this postmapping method.
    private Users addUser(@RequestBody Users users){
        return userService.addUser(users);
    }


    @GetMapping("/{userid}") // @PathVariable is an annotation used in a Spring MVC controller to extract values from the URI path.
    private Users getUser(@PathVariable("userid") Integer userId){
        return userService.getUser(userId);
    }

    @GetMapping("/allUsers")
    List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser/{userid}")
    private Users updateUser(@RequestBody Users newUser, @PathVariable("userid") Integer userID){
        return userService.updateUser(newUser, userID);
    }

    @DeleteMapping("/deleteUser/{userid}")
    public String deleteUser(@PathVariable Integer userid){
        return userService.deleteUser(userid);
    }









}
