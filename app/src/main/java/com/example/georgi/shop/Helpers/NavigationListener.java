package com.example.georgi.shop.Helpers;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.example.georgi.shop.Activities.AddProductActivity;
import com.example.georgi.shop.Activities.DeliverInfoActivity;
import com.example.georgi.shop.Activities.FavoriteProductsActivity;
import com.example.georgi.shop.Activities.FindProductActivity;
import com.example.georgi.shop.Activities.MainActivity;
import com.example.georgi.shop.Activities.NotificationActivity;
import com.example.georgi.shop.Activities.ProfileActivity;
import com.example.georgi.shop.Activities.ShopInfoActivity;
import com.example.georgi.shop.Activities.ShoppingBasketActivity;

/**
 * Created by Georgi on 04-May-17.
 */

public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener{

    private Activity activity;
    public NavigationListener(Activity activity){
        this.activity = activity;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String option = item.toString();
        switch (option){
            case "Acasa":
                goAcasa();
                return true;
            case "Produse":
                goProduce();
                return true;
            case "Favorite":
                goFavorite();
                return true;
            case "Cos":
                goCos();
                return true;
            case "Contul meu":
                goCont();
                return true;
            case "Notificari":
                goNotificari();
                return true;
            case "Informatii livrare":
                goInformatiiLivrare();
                return true;
            case "Informatii SHOP":
                goInformatiiShop();
                return true;
            case "Adauga produs":
                goAddProduct();
                return true;
            case "Modifica produs":
                goUpdateProduct();
                return true;
            case "Vizualizare comenzi":
                goViewOrders();
                return true;

        }
        return false;
    }

    private void goAcasa(){
        if(!(activity instanceof MainActivity)) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goProduce(){
        if(!(activity instanceof FindProductActivity)) {
            Intent intent = new Intent(activity, FindProductActivity.class);
            intent.putExtra("category", "Shop");
            activity.startActivity(intent);
        }
    }
    private void goFavorite(){
        if(!(activity instanceof FavoriteProductsActivity)) {
            Intent intent = new Intent(activity, FavoriteProductsActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goCos(){
        if(!(activity instanceof ShoppingBasketActivity)) {
            Intent intent = new Intent(activity, ShoppingBasketActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goCont(){
        if(!(activity instanceof ProfileActivity)) {
            Intent intent = new Intent(activity, ProfileActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goNotificari(){
        if(!(activity instanceof NotificationActivity)) {
            Intent intent = new Intent(activity, NotificationActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goInformatiiLivrare(){
        if(!(activity instanceof DeliverInfoActivity)) {
            Intent intent = new Intent(activity, DeliverInfoActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goInformatiiShop(){
        if(!(activity instanceof ShopInfoActivity)) {
            Intent intent = new Intent(activity, ShopInfoActivity.class);
            activity.startActivity(intent);
        }
    }

    private void goAddProduct(){
        if(!(activity instanceof AddProductActivity)) {
            Intent intent = new Intent(activity, AddProductActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goUpdateProduct(){
        if(!(activity instanceof DeliverInfoActivity)) {
            Intent intent = new Intent(activity, DeliverInfoActivity.class);
            activity.startActivity(intent);
        }
    }
    private void goViewOrders(){
        if(!(activity instanceof ShopInfoActivity)) {
            Intent intent = new Intent(activity, ShopInfoActivity.class);
            activity.startActivity(intent);
        }
    }
}
