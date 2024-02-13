package com.project.instagramclonebackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//name is what will be save in the DB as
@Entity(name="Users")
public class Users {

    //With GenerationType.IDENTITY, the database will automatically assign IDs in sequential order as new records are inserted.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    private String userName;
    private String name;
    private String profileImage;

    public Users(){
        super();
    }

    public Users( String userName, String name, String profileImage) {
        super();
        this.userName = userName;
        this.name = name;
        this.profileImage = profileImage;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
