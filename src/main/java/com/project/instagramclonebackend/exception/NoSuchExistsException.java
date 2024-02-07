
//  Creating custom exception that can be thrown when
// user tries to update/delete a user that doesn't exists

package com.project.instagramclonebackend.exception;


public class NoSuchExistsException extends RuntimeException{
   // private String message;

    public NoSuchExistsException() {}

    public NoSuchExistsException(String message) {
        super(message);
    }

    public NoSuchExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
