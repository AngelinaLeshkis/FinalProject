package com.leverx.dealerstat.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "trader", catalog = "dealerstatdb")
@EntityListeners(AuditingEntityListener.class)
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name of trader is a required field")
    private String nameOfTrader;
    private float ratingOfTrader;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    private boolean approved;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trader")
    private List<Comment> comments;

    public Trader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfTrader() {
        return nameOfTrader;
    }

    public void setNameOfTrader(String nameOfTrader) {
        this.nameOfTrader = nameOfTrader;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Float getRatingOfTrader() {
        return ratingOfTrader;
    }

    public void setRatingOfTrader(Float ratingOfTrader) {
        this.ratingOfTrader = ratingOfTrader;
    }

}
