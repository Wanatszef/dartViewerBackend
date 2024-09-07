package com.w4n4.dartViewer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "friend")
public class Friend
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fiend_id")
    private User friend;

    private String status = "pending";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
