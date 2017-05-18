package com.example.georgi.shop.Models;

/**
 * Created by Georgi on 18-May-17.
 */

public enum CommandEnum {

    ViewProductCommand,
    ViewCategoryCommand,
    ViewFavoriteCommand,
    ViewOrderedCommand,
    ViewReviewCommand,
    ViewBasketCommand,
    ViewUserCommand,

    AddProductCommand,
    AddFavoriteCommand,
    AddOrderedCommand,
    AddReviewCommand,
    AddBasketCommand,
    AddwUserCommand,


    UpdateProductCommand,
    UpdateFavoriteCommand,
    UpdateReviewCommand,
    UpdateBasketCommand,
    UpdateUserCommand,



    RemoveProductCommand,
    RemoveFavoriteCommand,
    RemoveReviewCommand,
    RemoveUserCommand,


    GetProductCommand,
    GetCategoryCommand,
    GetFavoriteByUserCommand,
    GetOrderedByUserCommand,
    GetReviewByProductCommand,
    GetBasketByUserCommand,
    GetUserCommand,


}
