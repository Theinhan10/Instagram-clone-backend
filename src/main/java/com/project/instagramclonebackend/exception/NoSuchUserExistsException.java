
//  Creating custom exception that can be thrown when
// user tries to update/delete a user that doesn't exists

package com.project.instagramclonebackend.exception;


public class NoSuchUserExistsException extends RuntimeException{
   // private String message;

    public NoSuchUserExistsException() {}

    public NoSuchUserExistsException(String message) {
        super(message);
    }
}
