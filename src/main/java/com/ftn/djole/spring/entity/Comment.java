package com.ftn.djole.spring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id",unique = true,nullable = false)
    private Integer id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false)
    private Date date;
    @Column(name = "likes",nullable = false)
    private Integer likes;
    @Column(name = "dislike",nullable = false)
    private  Integer dislike;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id",referencedColumnName = "post_id",nullable = false)
    private Post post;


    public Comment() {
    }

    public Comment(String title, String description, Date date, Integer likes, Integer dislike, User user, Post post) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.likes = likes;
        this.dislike = dislike;
        this.user = user;
        this.post = post;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                ", dislike=" + dislike +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
