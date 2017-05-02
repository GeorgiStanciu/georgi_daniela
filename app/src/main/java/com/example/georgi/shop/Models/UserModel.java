package com.example.georgi.shop.Models;

/**
 * Created by Georgi on 02-May-17.
 */

public class UserModel {
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
}
