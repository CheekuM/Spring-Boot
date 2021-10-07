package com.rest.services.restfulservices.user;

import com.rest.services.restfulservices.post.Post;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
    //Structure from log - create table user (id integer not null, date timestamp, name varchar(255), primary key (id))
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Size not less than 2")
    private String name;

    @Past
    private Date date;

    @OneToMany(mappedBy="user")
    private List<Post> posts;



    User(Integer id, String name, Date date) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
    }

    User() {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", date=" + date + "]";
    }

}
