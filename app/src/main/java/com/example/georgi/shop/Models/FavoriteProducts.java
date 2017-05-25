package com.example.georgi.shop.Models;

import java.io.Serializable;

/**
 * Created by Georgi on 10-May-17.
 */

public class FavoriteProducts implements Serializable{

    private int id;
    private UserModel user;
    private Product product;

    public FavoriteProducts(int id, UserModel user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public FavoriteProducts( UserModel user, Product product) {
        this.user = user;
        this.product = product;
    }
    public FavoriteProducts(){

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
