package com.example.georgi.shop.Helpers;

/**
 * Created by Georgi on 09-May-17.
 */

public class OnProductDeletedBasket {

    private int position;

    public OnProductDeletedBasket(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
