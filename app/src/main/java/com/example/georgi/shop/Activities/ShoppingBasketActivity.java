package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Models.ShoppingBasket;
import com.example.georgi.shop.R;

public class ShoppingBasketActivity extends BaseActivity {

    private ShoppingBasket basket;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_shopping_basket,null);
        RelativeLayout emptyBasket = (RelativeLayout) view.findViewById(R.id.empty_shop);
        LinearLayout basketLayout = (LinearLayout) view.findViewById(R.id.shopping_basket_layout);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString(getString(R.string.user_id_preference), "");
        if(userId.equals("") || basket == null || basket.getProducts() == null || basket.getProducts().size() == 0){
            emptyBasket.setVisibility(View.VISIBLE);
            basketLayout.setVisibility(View.GONE);
            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_basket);
            Button emptyButton = (Button) view.findViewById(R.id.empty_shop_button);
            if(userId.equals("")){
                emptyText.setText("Autentifica-te pentru a sincroniza \\n produsele din cos in contul tau.");
                emptyButton.setText("Autentifica-te");
                emptyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShoppingBasketActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
            else{
                emptyText.setText("Gaseste cele mai bune oferte \n si cumpara in siguranta!");
                emptyButton.setText("Vezi produce in magazin");
                emptyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ShoppingBasketActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }
        }
        else{
            emptyBasket.setVisibility(View.GONE);
            basketLayout.setVisibility(View.VISIBLE);
        }
        contentLayout.addView(view);

    }
}
