package com.example.georgi.shop.Helpers;

/**
 * Created by Georgi on 23-May-17.
 */

public class OnReviewAdded  {

    private float rating;
    private int count;

    public OnReviewAdded(float rating, int count){
        this.rating = rating;
        this.count = count;
    }

    public float getRating(){
        return rating;
    }

    public int getCount() {
        return count;
    }
}
