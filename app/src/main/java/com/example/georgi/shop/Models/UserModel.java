package com.example.georgi.shop.Models;

import java.io.Serializable;

/**
 * Created by Georgi on 02-May-17.
 */

public class UserModel implements Serializable {
    private String name;
    private String email;
    private String id;
    private String address;
    private String birthDate;
    private String pictureUrl;

    public UserModel(){
        this.name = "";
        this.email = "";
        this.id = "";
    }
    public UserModel(String email, String id, String name){
        this.email = email;
        this.id  = id;
        this.name = name;
    }

    public UserModel( String id, String email, String name, String address, String birthDate){
        this.email = email;
        this.id  = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.pictureUrl = "";
    }
    public UserModel( String id, String email, String name, String address, String birthDate, String url){
        this.email = email;
        this.id  = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.pictureUrl = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
