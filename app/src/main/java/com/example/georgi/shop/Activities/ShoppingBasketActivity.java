package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Adapters.BasketProductAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeletedBasket;
import com.example.georgi.shop.Helpers.OnTotalSumChange;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.ShoppingBasket;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;
import com.squareup.otto.Subscribe;

public class ShoppingBasketActivity extends BaseActivity {

    private ShoppingBasket basket;
    private TextView sum;
    private View view;
    private  RelativeLayout emptyBasket;
    private LinearLayout basketLayout;
   private ProgressBar progressBar;
    private  int userId;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_shopping_basket,null);
        emptyBasket = (RelativeLayout) view.findViewById(R.id.empty_shop);
        basketLayout = (LinearLayout) view.findViewById(R.id.shopping_basket_layout);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        if(userId == 0) {
            progressBar.setVisibility(View.GONE);
            emptyBasket.setVisibility(View.VISIBLE);
            basketLayout.setVisibility(View.GONE);
            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_basket);
            Button emptyButton = (Button) view.findViewById(R.id.empty_shop_button);
            emptyText.setText("Autentifica-te pentru a sincroniza \n produsele din cos in contul tau.");
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
            new GetBasket(userId).execute();

        }

            contentLayout.addView(view);

    }


    private void setProducts(){

        progressBar.setVisibility(View.GONE);
        if(basket == null || basket.getProducts() == null || basket.getProducts().size() == 0){
            emptyBasket.setVisibility(View.VISIBLE);
            basketLayout.setVisibility(View.GONE);
            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_basket);
            Button emptyButton = (Button) view.findViewById(R.id.empty_shop_button);


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
        else{
            ListView productList = (ListView) view.findViewById(R.id.shop_product_list);
            sum = (TextView) view.findViewById(R.id.total_sum);

            emptyBasket.setVisibility(View.GONE);
            basketLayout.setVisibility(View.VISIBLE);

            Button continueButton = (Button) view.findViewById(R.id.continue_shop_button);

            BasketProductAdapter adapter = new BasketProductAdapter(this, basket.getProducts(), basket.getProductsNumber(), userId);
            productList.setAdapter(adapter);
            sum.setText(String.format("%.2f", basket.calcSum()) + " Lei");

            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ShoppingBasketActivity.this, PayMethodActivity.class);
                    intent.putExtra("shoppingBasket", basket);
                    startActivity(intent);
                }
            });
        }
    }

    @Subscribe
    public void OnProductDeleted(OnProductDeletedBasket event){
        int position = event.getPosition();
        basket.getProducts().remove(position);
        basket.getProductsNumber().remove(position);
        sum.setText(String.format("%.2f", basket.calcSum()) + " Lei");
        if(basket.getProducts().size() == 0)
            refresh();
    }


    @Subscribe
    public void OnTotalSumChange(OnTotalSumChange event){
        sum.setText(String.format("%.2f", basket.calcSum()) + " Lei");
    }

    private void refresh(){
        Intent intent = new Intent(this, ShoppingBasketActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GlobalBus.getBus().unregister(this);
    }

    class GetBasket extends AsyncTask{

        int userId;
        GetBasket(int userId){
            this.userId = userId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetBasketByUserCommand, userId));
            basket = (ShoppingBasket) response.getResponse();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
               setProducts();

        }
    }

}
