package com.ftn.djole.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable,UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true,nullable = false)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Comment> comments=new HashSet<Comment>();

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Post> posts=new HashSet<Post>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private Set<Authority>userAuthority=new HashSet<Authority>();
    public User() {
    }

    public User(String name, String username, String password, byte[] photo, Set<Comment> comments, Set<Post> posts, Set<Authority> userAuthority) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.comments = comments;
        this.posts = posts;
        this.userAuthority = userAuthority;
    }

    public void add(Post p){
        if(p.getUser()!=null)
            p.getUser().getPosts().remove(p);
        p.setUser(this);
        getPosts().add(p);
    }

    public  void remove(Post p){
        p.setUser(null);
        getPosts().remove(p);
    }

    public void add(Comment c){
        if(c.getUser() != null)
            c.getUser().getComments().remove(c);
        c.setUser(this);
        getComments().add(c);
    }

    public void remove(Comment c){
        c.setUser(null);
        getComments().remove(c);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Authority> getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Set<Authority> userAuthority) {
        this.userAuthority = userAuthority;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuthority;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", comments=" + comments +
                ", posts=" + posts +
                ", userAuthority=" + userAuthority +
                '}';
    }
}
