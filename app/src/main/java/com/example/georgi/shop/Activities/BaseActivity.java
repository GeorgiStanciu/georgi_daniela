package com.example.georgi.shop.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.NavigationListener;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {

    private int userId;
    protected LinearLayout contentLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SHOP");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar.setNavigationIcon(R.mipmap.ic_view_headline);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
        if(userId == 0){
            setMenu(R.menu.drawer_menu);
        }
        else{
            new GetUser().execute();
        }
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        addLayout();
    }

    private void setMenu(int menu){
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.inflateMenu(menu);
        navigation.setNavigationItemSelectedListener(new NavigationListener(this));
    }

    protected abstract void addLayout();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new FindProducts(query).execute();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteIcon  = favoriteItem.getIcon();
        favoriteIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        MenuItem basketItem = menu.findItem(R.id.shopCart);
        Drawable basketIcon  = basketItem.getIcon();
        basketIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.shopCart:
                Intent intent = new Intent(this, ShoppingBasketActivity.class);
                startActivity(intent);
                return  true;
            case R.id.favorite:
                Intent favoriteIntent = new Intent(this, FavoriteProductsActivity.class);
                startActivity(favoriteIntent);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    class FindProducts extends AsyncTask{

        String find;
        ArrayList<Product> products;
        FindProducts(String find){
            this.find = find;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();

            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetProductsByString, find));
            products = (ArrayList<Product>) response.getResponse();

            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("products", products);
            startActivity(intent);
        }
    }

    class GetUser extends AsyncTask{

        UserModel user;
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand, userId));

            user = (UserModel) response.getResponse();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(user != null)
                if(user.getFunction() == null)
                    setMenu(R.menu.drawer_menu);
                else if(user.getFunction().equals("admin"))
                    setMenu(R.menu.admin_menu);
        }
    }
}
