package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Georgi on 02-May-17.
 */

public class Product implements Serializable{

    private String id;
    private String name;
    private String description;
    private float price;
    private ArrayList<String> images;
    private String category;
    private int discount;
    private String seller;
    private int surety;
    private int quantity;



    public Product(String id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public Product(String id, String name, String description, ArrayList<String> images, String category, float price, int discount, String seller, int surety, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.category = category;
        this.discount = discount;
        this.seller = seller;
        this.surety = surety;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getSurety() {
        return surety;
    }

    public void setSurety(int surety) {
        this.surety = surety;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
