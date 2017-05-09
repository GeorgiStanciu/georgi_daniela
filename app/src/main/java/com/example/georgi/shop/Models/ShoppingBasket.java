package com.example.georgi.shop.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Georgi on 08-May-17.
 */

public class ShoppingBasket implements Serializable{

    private String id;
    private UserModel user;
    private ArrayList<Product> products;
    private ArrayList<Integer> productsNumber;

    public ShoppingBasket(String id, UserModel user, ArrayList<Product> products, ArrayList<Integer> productsNumber) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.productsNumber = productsNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public float calcSum(){
        float sum = 0.0f;
        for(int i = 0; i < products.size(); i++){
            float newSum = products.get(i).getPrice() - products.get(i).getPrice() * products.get(i).getDiscount() / 100;
            sum += newSum * productsNumber.get(i);
        }
        return sum;
    }
}
