package com.example.georgi.shop.Helpers;

import java.io.Serializable;

/**
 * Created by Georgi on 18-May-17.
 */

public class CommandResponse implements Serializable {

    private Object object;

    public  void setResponse(Object object){

        this.object = object;
    }
    public Object getResponse(){
        return object;
    }

}
