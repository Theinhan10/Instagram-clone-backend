package com.project.instagramclonebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

// This class is annotated with @ControllerAdvice, indicating that it provides global exception handling advice for controllers.
@ControllerAdvice
public class UserNotFoundAdvice {

    // This method is annotated with @ExceptionHandler(NoSuchUserExistsException.class),
    // indicating that it handles exceptions of type NoSuchUserExistsException.
    @ResponseBody
    @ExceptionHandler(NoSuchUserExistsException.class)
    // Set the HTTP response status to NOT_FOUND (404) when this exception occurs.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> userExceptionHandler(NoSuchUserExistsException exception) {
        // This code is executed when an exception of type NoSuchUserExistsException occurs.

        // Create a Map to store the error message.
        Map<String, String> errorMap = new HashMap<>();

        // Put the error message from the exception into the Map.
        errorMap.put("Error Message", exception.getMessage());

        // Return the Map containing the error message.
        return errorMap;
    }
}
