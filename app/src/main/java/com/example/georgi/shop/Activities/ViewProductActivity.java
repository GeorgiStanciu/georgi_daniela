package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.georgi.shop.Adapters.ProductPagerAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ProductBasket;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

public class ViewProductActivity extends BaseActivity {

    private Product product;
    private boolean isFavorite;
    private ProductPagerAdapter adapter;
    private ViewPager viewPager;
    private int userId;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_view_product,null);

        Intent intent = getIntent();
        final int productId = intent.getIntExtra("productId",0);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        FloatingActionButton basket = (FloatingActionButton) view.findViewById(R.id.add_to_basket);
        /*ProductPagerAdapter adapter = new ProductPagerAdapter(getSupportFragmentManager(), product);
        viewPager.setAdapter(adapter);*/
        new GetProduct(productId).execute();

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userId == 0){
                    Toast.makeText(getBaseContext(), "You must login to add products to your basket", Toast.LENGTH_SHORT).show();
                }
                else{

                    ProductBasket productBasket = new ProductBasket(userId, productId, 1);
                    new AddToBasket(productBasket).execute();
                }
            }
        });
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

            if(userId != 0) {
                CommandResponse user = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand, userId));
                FavoriteProducts favorite = new FavoriteProducts((UserModel) user.getResponse(), product);
                CommandResponse responseFavoriet = client.receiveDataFromServer(new Command(CommandEnum.GetIsFavoriteProductCommand, favorite));
                isFavorite = (boolean) responseFavoriet.getResponse();
            }
            else
                isFavorite = false;
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            adapter = new ProductPagerAdapter(getSupportFragmentManager(), product, isFavorite);
            viewPager.setAdapter(adapter);

        }
    }


    class AddToBasket extends  AsyncTask{

        private ProductBasket productBasket;

        AddToBasket(ProductBasket productBasket){
            this.productBasket = productBasket;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client =  new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.AddBasketCommand.AddProductToBasketCommand, productBasket));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
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
