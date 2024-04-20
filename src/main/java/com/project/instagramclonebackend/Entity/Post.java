package com.project.instagramclonebackend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotNull
    private String userUID;
    private String userName;
    private String path;
    private Timestamp timestamp;
    private int likeCount;


    @ElementCollection
    private List<String> images = new ArrayList<>();


    //default Constructor
    public Post(){
        super();
    }

    public Post(Long postId, String userUID, String userName, String path, Timestamp timestamp, int likeCount, List<String> images) {
        this.postId = postId;
        this.userUID = userUID;
        this.userName = userName;
        this.path = path;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
