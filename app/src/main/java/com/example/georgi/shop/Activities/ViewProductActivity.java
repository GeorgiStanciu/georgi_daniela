package com.example.georgi.shop.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.georgi.shop.Adapters.ProductPagerAdapter;
import com.example.georgi.shop.Helpers.NavigationListener;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SHOP");
        toolbar.setTitleTextColor(getResources().getColor(R.color.chiffon));
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar.setNavigationIcon(R.mipmap.ic_view_headline);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationListener(this));


        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ProductPagerAdapter adapter = new ProductPagerAdapter(getSupportFragmentManager(), product);
        viewPager.setAdapter(adapter);
    }

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
}
