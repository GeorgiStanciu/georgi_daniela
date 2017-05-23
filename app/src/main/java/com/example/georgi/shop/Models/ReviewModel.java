package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Georgi on 06.05.2017.
 */

public class ReviewModel implements Serializable{

    private int id;
    private String title;
    private String review;
    private UserModel user;
    private Date date;
    private int qualifying;
    private int productId;


    public ReviewModel(int id, String title, String review, UserModel user, Date date, int qualifying) {
        this.id = id;
        this.title = title;
        this.review = review;
        this.user = user;
        this.date = date;
        this.qualifying = qualifying;
    }


    public ReviewModel(String title, String review, UserModel user, Date date, int qualifying) {
        this.title = title;
        this.review = review;
        this.user = user;
        this.date = date;
        this.qualifying = qualifying;
    }


    public ReviewModel(String title, String review, UserModel user, Date date, int qualifying, int productId) {
        this.title = title;
        this.review = review;
        this.user = user;
        this.date = date;
        this.qualifying = qualifying;
        this.productId = productId;
    }

    public ReviewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQualifying() {
        return qualifying;
    }

    public void setQualifying(int qualifying) {
        this.qualifying = qualifying;
    }

}
