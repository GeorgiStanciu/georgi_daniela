package com.example.georgi.shop.Helpers;

import com.example.georgi.shop.Models.Category;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.OrderModel;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.ShoppingBasket;
import com.example.georgi.shop.Models.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
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
        SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();

        /*GsonBuilder gsonBuilder = new GsonBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        gsonBuilder.setDateFormat("yyyy/MM/dd");*/
        Gson gson = new GsonBuilder().registerTypeAdapter(java.sql.Date.class, new JsonDateDeserializer())
                                     .create();
        CommandResponse commandResponse = null;

        if(command == CommandEnum.ViewProductsCommand || command == CommandEnum.GetProductByCategoryCommand) {
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }
        else if(command == CommandEnum.ViewCategoriesCommandByParent) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<Category>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }
        else if(command == CommandEnum.ViewOrderesCommand) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<OrderModel>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }
        else if(command == CommandEnum.ViewUsersCommand) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<UserModel>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }


        else if(command == CommandEnum.GetFavoritesByUserCommand)
        {JsonArray jsonArray = (JsonArray)jsonObject.get("object");
        Type type = new TypeToken<ArrayList<FavoriteProducts>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }


        else if(command == CommandEnum.GetBasketByUserCommand) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<ShoppingBasket>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }

        else if(command == CommandEnum.GetOrderesByUserCommand) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<OrderModel>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }


        else if(command == CommandEnum.GetReviewsByProductCommand) {
            JsonArray jsonArray = (JsonArray)jsonObject.get("object");
            Type type = new TypeToken<ArrayList<ReviewModel>>() {}.getType();
            Object object = gson.fromJson(jsonArray, type);
            commandResponse = new CommandResponse(object);
        }

        else if(command == CommandEnum.GetProductCommand) {
            JsonObject jsonArray = (JsonObject)jsonObject.get("object");
            Object object = gson.fromJson(jsonArray, Product.class);
            commandResponse = new CommandResponse(object);
        }

        else if(command == CommandEnum.GetUserCommand) {
            JsonObject jsonArray = (JsonObject) jsonObject.get("object");
            Object object = gson.fromJson(jsonArray, UserModel.class);
            commandResponse = new CommandResponse(object);
        }

        else if(command == CommandEnum.AddUserCommand){
            JsonPrimitive jsonObj = (JsonPrimitive) jsonObject.get("object");
            Object object = gson.fromJson(jsonObj, Integer.class);
            commandResponse = new CommandResponse(object);
        }
        return commandResponse;
    }

}
