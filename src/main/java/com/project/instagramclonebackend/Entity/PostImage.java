package com.project.instagramclonebackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "PostImage")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImageId;
    private String imageUrl;

    //@JsonManagedReference annotation is used  parent side of the relationship.  indicates the annotated property is the "forward" part of the reference, and it should be serialized normally.
    //The @JsonBackReference annotation used on child side of relationship. indicate back part of reference and should be serialized.
    //Serialized mean  like object converting its state into a JSON string like {"name": "John Doe", "age": 30, "address": "123 Main St"}
    //Deserialized mean Taking JSON string and converting back into obect with attributes
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private Post post;

    // default Constructor
    public PostImage() {
        super();
    }

    public PostImage(Long postImageId, String imageUrl, Long postId, Post post) {
        this.postImageId = postImageId;
        this.imageUrl = imageUrl;
        this.post = post;
    }


    public Long getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Long postImageId) {
        this.postImageId = postImageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }



}
