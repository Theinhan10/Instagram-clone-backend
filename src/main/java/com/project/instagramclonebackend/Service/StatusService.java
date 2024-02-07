package com.project.instagramclonebackend.Service;

import com.project.instagramclonebackend.Entity.Status;
import com.project.instagramclonebackend.Repository.StatusRepo;
import com.project.instagramclonebackend.exception.NoSuchExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StatusService {

    @Autowired
    StatusRepo statusRepo;

    //adding status
    public Status addStatus(Status status){
        try{
            return statusRepo.save(status);
        }catch (DataIntegrityViolationException e){
            // Handle exception or log an error message
            throw new NoSuchExistsException("Unable to add status: " + e.getMessage(), e);
        }
    }

    //Getting a specific status
    public Status getStatus(Integer statusId){
        return statusRepo.findById(statusId).orElseThrow(()-> new NoSuchElementException("No status was found with ID " + statusId));
    }

    //getting all of the status
    public List<Status> getAllStatus(){
        return statusRepo.findAll();
    }

    //deleting status
    public String deleteStatus(Integer statusId) {
        if(!statusRepo.existsById(statusId)){
            throw new NoSuchExistsException("Unable to delete Status with ID " + statusId);
        }
        statusRepo.deleteById(statusId);
        return "Status with id " + statusId + " has been deleted success.";
    }


}
