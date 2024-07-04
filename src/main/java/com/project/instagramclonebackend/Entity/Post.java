package com.project.instagramclonebackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Timestamp timestamp;
    private int likeCount;
    private String caption;



    //@JsonManagedReference annotation is used  parent side of the relationship.  indicates the annotated property is the "forward" part of the reference, and it should be serialized normally.
    //CascadeType.ALL: This means all operations (persist, merge, remove, refresh, detach) will be cascaded to the child entities (in this case, PostImage entities).
    //orphanRemoval = true: This ensures that if a PostImage entity is removed from the images list of a Post, it will also be removed from the database.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PostImage> images = new ArrayList<>();  //List of PostEntities that associate with the post




    //The @JsonBackReference annotation used on child side of relationship. indicate back part of reference and should be serialized.
    //Serialized mean  prepares data for storage or transmission by converting it into a specific format. like object converting its state into a JSON string like {"name": "John Doe", "age": 30, "address": "123 Main St"}
    //Deserialized mean Taking JSON string and converting back into obect with attributes. reconstructs data from its serialized format back into an object or data structure.
    @ManyToOne
    @JoinColumn(name = "userUniqueId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //Specifies when parent entity "Users" is deleted, related child entity "post" also be deleted.
    @JsonBackReference
    private Users user;  //field that show relationship between User and post entity

    /**
     Using @JsonManagedReference and @JsonBackReference helps manage and prevent issues with bidirectional relationships during JSON serialization and deserialization.
    **/


    //@Transient //@Transient indicates that this field should not be part of the database schema but is used only for processing incoming JSON data.
    @ElementCollection
    private List<String> imageUrls = new ArrayList<>(); //List of imageUrls

    // default Constructor
    public Post() {
        super();
    }

    public Post(Long postId, String userUID, String userName, Timestamp timestamp, int likeCount, String caption, List<String> imageUrls, Users user, List<PostImage> images) {
        this.postId = postId;
        this.userUID = userUID;
        this.userName = userName;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
        this.imageUrls = imageUrls;
        this.caption = caption;
        this.user = user;
        this.images = images;
    }

    // Getters and setters for all fields, including the new user field
    public List<PostImage> getImages() {
        return images;
    }

    public void setImages(List<PostImage> images) {
        this.images.clear();
        if (images != null) {
            for (PostImage postImage : images) {
                addImage(postImage);
            }
        }
    }

    public void addImage(PostImage image) {
        images.add(image);
        image.setPost(this);
    }

    public void removeImage(PostImage image) {
        images.remove(image);
        image.setPost(null);
    }


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
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


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
