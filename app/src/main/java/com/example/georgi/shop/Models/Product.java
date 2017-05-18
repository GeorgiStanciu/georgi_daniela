package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Georgi on 02-May-17.
 */

public class Product implements Serializable{

    private int id;
    private String name;
    private String description;
    private float price;
    private ArrayList<String> images;
    private String category;
    private int discount;
    private String seller;
    private int guarantee;
    private int quantity;
    private ArrayList<ReviewModel> reviews;
    private float rating;



    public Product(int id, String name, String description, String category, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.discount = 0;
        this.seller = "";
        this.guarantee = 0;
        this.quantity = -1;
        this.rating = 0.00f;
    }

    public Product( String name, String description, String category, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.discount = 0;
        this.seller = "";
        this.guarantee = 0;
        this.quantity = -1;
        this.rating = 0.00f;
    }


    public Product(int id, String name, String description, ArrayList<String> images, String category,
                   float price, int discount, String seller, int guarantee, int quantity, ArrayList<ReviewModel> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.category = category;
        this.discount = discount;
        this.seller = seller;
        this.guarantee = guarantee;
        this.quantity = quantity;
        this.reviews = reviews;
        this.rating = 0.00f;
    }

    public Product(int id, String name, String description, ArrayList<String> images, String category,
                   float price, int discount, String seller, int guarantee, int quantity, ArrayList<ReviewModel> reviews, float rating) {


        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.category = category;
        this.discount = discount;
        this.seller = seller;
        this.guarantee = guarantee;
        this.quantity = quantity;
        this.reviews = reviews;
        this.rating = rating;
    }

    public Product(String name, String description, ArrayList<String> images, String category,
                   float price, int discount, String seller, int guarantee, int quantity, ArrayList<ReviewModel> reviews, float rating) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.category = category;
        this.discount = discount;
        this.seller = seller;
        this.guarantee = guarantee;
        this.quantity = quantity;
        this.reviews = reviews;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<ReviewModel> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewModel> reviews) {
        this.reviews = reviews;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void addReview(ReviewModel review){
        if(reviews == null) {
            reviews = new ArrayList<>();
        }
        rating = ((rating * (reviews.size() - 2)) + review.getQualifying()) / (reviews.size() - 1);
        reviews.add(review);

    }

}


