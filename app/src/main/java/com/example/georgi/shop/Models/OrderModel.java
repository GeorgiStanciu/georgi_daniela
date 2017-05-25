package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Georgi on 11-May-17.
 */

public class OrderModel implements Serializable {

    private int id;
    private UserModel user;
    private ArrayList<Product> products;
    private ArrayList<Integer> productNumber;
    private Date orderDate;
    private float cost;
    public OrderModel(int id, UserModel user, ArrayList<Product> products, ArrayList<Integer> productNumber, Date orderDate, float cost) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.productNumber = productNumber;
        this.cost = cost;
        this.orderDate = orderDate;
    }
    public OrderModel( UserModel user, ArrayList<Product> products, ArrayList<Integer> productNumber, Date orderDate, float cost) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.productNumber = productNumber;
        this.cost = cost;
        this.orderDate = orderDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(ArrayList<Integer> productNumber) {
        this.productNumber = productNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
