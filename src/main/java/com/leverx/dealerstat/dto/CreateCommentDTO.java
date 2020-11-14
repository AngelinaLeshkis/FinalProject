package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.entity.Comment;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class CreateCommentDTO {

    private Long id;
    private String text;
    private double rating;
    private Date dateOfCreation;
    private boolean approved;
    private Long traderId;

    public CreateCommentDTO() {

    }

    public CreateCommentDTO(Long id, String text, double rating, Date dateOfCreation,
                            boolean approved, Long traderId) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.dateOfCreation = dateOfCreation;
        this.approved = approved;
        this.traderId = traderId;
    }

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

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }
}
