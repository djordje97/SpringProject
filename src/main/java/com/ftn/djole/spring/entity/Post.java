package com.ftn.djole.spring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id",nullable = false,unique = true)
    private Integer id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false)
    private Date date;
    @Column(name = "likes",nullable = false)
    private Integer likes;
    @Column(name = "dislikes",nullable = false)
    private Integer dislike;
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "latitude")
    private float latitude;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    private  User user;
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Comment> comments=new HashSet<Comment>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "post_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<Tag>();

    public Post() {
    }

    public Post(String title, String description, byte[] photo, Date date, Integer likes, Integer dislike, float longitude, float latitude, User user, Set<Comment> comments, Set<Tag> tags) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.likes = likes;
        this.dislike = dislike;
        this.longitude = longitude;
        this.latitude = latitude;
        this.user = user;
        this.comments = comments;
        this.tags = tags;
    }

    public void add(Comment c){
        if(c.getPost()!= null)
            c.getPost().getComments().remove(c);
        c.setPost(this);
        getComments().add(c);
    }

    public void remove(Comment c){
        c.setPost(null);
        getComments().remove(c);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", date=" + date +
                ", likes=" + likes +
                ", dislike=" + dislike +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", user=" + user +
                ", comments=" + comments +
                ", tags=" + tags +
                '}';
    }
}
