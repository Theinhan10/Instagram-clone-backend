package com.project.instagramclonebackend.Controller;

import com.project.instagramclonebackend.Entity.Status;
import com.project.instagramclonebackend.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @PostMapping("")
    private Status addStatus(@RequestBody Status status){
        return statusService.addStatus(status);
    }

    @GetMapping("/{statusId}")
    private Status getStatus(@PathVariable("statusId") Integer statusId){
        return statusService.getStatus(statusId);
    }

    @GetMapping("/getAllStatus")
    private List<Status> getAllStatus(){
        return statusService.getAllStatus();
    }

    @DeleteMapping("/deleteStatus/{statusId}")
    public String deleteStatus(@PathVariable Integer statusId){
        return statusService.deleteStatus(statusId);
    }

}
