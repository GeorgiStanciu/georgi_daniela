package com.example.georgi.shop.Helpers;

/**
 * Created by Georgi on 02-May-17.
 */

public class OnUserLogin {

    private boolean login;

    public OnUserLogin(boolean login){
        this.login = login;
    }

    public boolean getLoginStatus(){
        return login;
    }

}
