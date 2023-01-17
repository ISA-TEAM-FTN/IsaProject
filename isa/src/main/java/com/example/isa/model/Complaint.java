package com.example.isa.model;

public class Complaint extends Entity {

    private String Comment;

    private User user; //user has more complains, manyToOne

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
