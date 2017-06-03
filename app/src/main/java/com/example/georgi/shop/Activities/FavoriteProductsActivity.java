package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeleted;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class FavoriteProductsActivity extends BaseActivity {

    private ArrayList<FavoriteProducts> favorites;
    private  int userId;
    private View view;
    private RelativeLayout emptyFavorite;
    private  GridView gridView ;
    private ProgressBar progressBar;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_favorite_products,null);
        emptyFavorite = (RelativeLayout) view.findViewById(R.id.empty_favorite_layout);
        gridView = (GridView) view.findViewById(R.id.favorite_products_grid);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        final SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
        new GetFavoriteProducts().execute();

        if(userId == 0 ){
            progressBar.setVisibility(View.GONE);
            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_favorite);
            Button emptyButton = (Button) view.findViewById(R.id.empty_favorite_button);
            emptyText.setText("Autentifica-te pentru a sincroniza \n produsele din lista de favorite.");
            emptyButton.setText("Autentifica-te");
            emptyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FavoriteProductsActivity.this, LoginActivity.class);
                    startActivity(intent);
            }
            });
        }

        contentLayout.addView(view);
    }

    private void refresh(){
        Intent intent = new Intent(this, FavoriteProductsActivity.class);
        startActivity(intent);
        finish();

    }

    @Subscribe
    public void OnProductDeleted(OnProductDeleted event){
        if(event.getPosition() == 0)
            refresh();
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


    private void setFavorites(){
        progressBar.setVisibility(View.GONE);
        if( favorites == null || favorites.size() == 0){
            emptyFavorite.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);

            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_favorite);
            Button emptyButton = (Button) view.findViewById(R.id.empty_favorite_button);
            emptyText.setText("Gaseste cele mai bune oferte \n si cumpara in siguranta!");
            emptyButton.setText("Vezi produce in magazin");
            emptyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(FavoriteProductsActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

        }

        else{
            emptyFavorite.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            ArrayList<Product> products = new ArrayList<>();
            for(FavoriteProducts favorite: favorites){
                products.add(favorite.getProduct());
            }
            ProductAdapter adapter = new ProductAdapter(this, products, R.menu.favorite_menu, false);
            gridView.setAdapter(adapter);
        }

    }
    class GetFavoriteProducts extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetFavoritesByUserCommand, userId));
            favorites = (ArrayList<FavoriteProducts>) response.getResponse();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            setFavorites();
        }
    }

}
