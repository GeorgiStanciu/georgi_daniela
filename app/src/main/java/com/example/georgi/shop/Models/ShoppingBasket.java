package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Georgi on 08-May-17.
 */

public class ShoppingBasket implements Serializable{

    private int id;
    private UserModel user;
    private ArrayList<Product> products;
    private ArrayList<Integer> productsNumber;

    public ShoppingBasket(int id, UserModel user, ArrayList<Product> products, ArrayList<Integer> productsNumber) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.productsNumber = productsNumber;
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

    public ArrayList<Integer> getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(ArrayList<Integer> productsNumber) {
        this.productsNumber = productsNumber;
    }
}
