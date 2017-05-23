package com.example.georgi.shop.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.georgi.shop.Adapters.ProductPagerAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

public class ViewProductActivity extends BaseActivity {

    private Product product;
    private ProductPagerAdapter adapter;
    private ViewPager viewPager;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_view_product,null);

        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId",0);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        /*ProductPagerAdapter adapter = new ProductPagerAdapter(getSupportFragmentManager(), product);
        viewPager.setAdapter(adapter);*/
        new GetProduct(productId).execute();
        contentLayout.addView(view);
    }


    class GetProduct extends AsyncTask {

        private int productId;
        GetProduct(int productId){
            this.productId = productId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client =  new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetProductCommand,productId));
            product = (Product) response.getResponse();
            Client c2 = new Client();

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            adapter = new ProductPagerAdapter(getSupportFragmentManager(), product);
            viewPager.setAdapter(adapter);
        }
    }

    /* @Override
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
    }*/
}
