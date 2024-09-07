package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

public class UserDto
{

    private Integer id;

    private String username;


    private String email;

    private int rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserDto(Integer id, String username,  String email, int rating) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rating = rating;
    }

    public UserDto(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.rating = user.getRating();
    }

    public UserDto()
    {

    }
}
