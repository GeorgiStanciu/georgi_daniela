package com.example.georgi.shop.Helpers;

import com.example.georgi.shop.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Georgi on 19-May-17.
 */

public class DeserializateResponse {

    private String response;
    private CommandEnum command;
    public DeserializateResponse(String response, CommandEnum command) {

        this.response = response;
        this.command = command;


    }

    public CommandResponse getResponse(){
        JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(response);
        JsonArray jsonArray = (JsonArray)jsonObject.get("object");
        Gson gson1 = new GsonBuilder().create();
        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
        Object object = gson1.fromJson(jsonArray, type);


        CommandResponse commandResponse = new CommandResponse(object);
        return commandResponse;
    }

}
