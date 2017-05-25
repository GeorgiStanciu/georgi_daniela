package com.example.georgi.shop.Models;

import java.io.Serializable;

/**
 * Created by Georgi on 25-May-17.
 */


public class ProductBasket implements Serializable {

    private int id;
    private int basketId;
    private int productId;
    private int quantity;
    private Product product;


    public ProductBasket(int id, int basketId, int productId, int quantity) {
        super();
        this.id = id;
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductBasket( int basketId, int productId, int quantity) {
        super();
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public ProductBasket(int id, int basketId, int productId, int quantity, Product product) {
        super();
        this.id = id;
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }


    public ProductBasket( int basketId, int productId, int quantity, Product product) {
        super();
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getBasketId() {
        return basketId;
    }


    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }


    public int getProductId() {
        return productId;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}

