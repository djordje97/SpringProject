package com.ftn.djole.spring.dto;

import com.ftn.djole.spring.entity.Post;

import java.io.Serializable;
import java.util.Date;

public class PostDTO implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private byte[] photo;
    private Date date;
    private Integer likes;
    private Integer dislike;
    private float longitude;
    private float latitude;
    private UserDTO user;

    public PostDTO() {
    }

    public PostDTO(Integer id, String title, String description, byte[] photo, Date date, Integer likes, Integer dislike, float longitude, float latitude, UserDTO user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.likes = likes;
        this.dislike = dislike;
        this.longitude = longitude;
        this.latitude = latitude;
        this.user = user;
    }

    public PostDTO(Post post) {
        this(post.getId(),post.getTitle(),post.getDescription(),post.getPhoto(),post.getDate(),post.getLikes(),post.getDislike(),post.getLongitude(),post.getLatitude(),new UserDTO(post.getUser()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
