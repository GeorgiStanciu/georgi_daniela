package com.example.georgi.shop.Helpers;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.example.georgi.shop.Activities.MainActivity;

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
            case "Produce":
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
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goFavorite(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goCos(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goCont(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goNotificari(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goInformatiiLivrare(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private void goInformatiiShop(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }


}
