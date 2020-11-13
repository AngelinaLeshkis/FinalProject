package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.entity.Comment;

import java.sql.Date;

public class CommentForNewTraderDTO {

    private Long id;
    private String text;
    private double rating;
    private Date dateOfCreation;
    private boolean approved;
    private String nameOfTrader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getNameOfTrader() {
        return nameOfTrader;
    }

    public void setNameOfTrader(String nameOfTrader) {
        this.nameOfTrader = nameOfTrader;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    //    public Comment toComment(CommentForNewTraderDTO commentForNewTrader) {
//        Comment comment = new Comment();
//
//        comment.setId(id);
//        comment.setText(text);
//        comment.setRating(rating);
//
//        return comment;
//    }
}
