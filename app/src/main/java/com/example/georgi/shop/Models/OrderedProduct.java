package com.example.georgi.shop.Models;

import java.io.Serializable;

/**
 * Created by Georgi on 25-May-17.
 */

public class OrderedProduct implements Serializable {

    private int id;
    private int orderId;
    private int productId;
    private Product product;
    private int quantity;
    private float cost;


    public OrderedProduct(int id, int orderId, int productId, int quantity, float cost) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public OrderedProduct(int orderId, int productId, int quantity, float cost) {
        super();
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }


    public OrderedProduct(int id, int orderId, int productId, int quantity, float cost, Product product) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
        this.product = product;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getOrderId() {
        return orderId;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getProductId() {
        return productId;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public float getCost() {
        return cost;
    }


    public void setCost(float cost) {
        this.cost = cost;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


}
