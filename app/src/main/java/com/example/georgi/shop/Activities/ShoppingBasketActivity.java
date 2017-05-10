package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Adapters.BasketProductAdapter;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeletedBasket;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.ShoppingBasket;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShoppingBasketActivity extends BaseActivity {

    private ShoppingBasket basket;
    private TextView sum;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_shopping_basket,null);
        RelativeLayout emptyBasket = (RelativeLayout) view.findViewById(R.id.empty_shop);
        LinearLayout basketLayout = (LinearLayout) view.findViewById(R.id.shopping_basket_layout);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString(getString(R.string.user_id_preference), "");
        populateBasket();

        if(userId.equals("") || basket == null || basket.getProducts() == null || basket.getProducts().size() == 0){
            emptyBasket.setVisibility(View.VISIBLE);
            basketLayout.setVisibility(View.GONE);
            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_basket);
            Button emptyButton = (Button) view.findViewById(R.id.empty_shop_button);
            if(userId.equals("")){
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
            ListView productList = (ListView) view.findViewById(R.id.shop_product_list);
            sum = (TextView) view.findViewById(R.id.total_sum);
            Button continueButton = (Button) view.findViewById(R.id.continue_shop_button);

            BasketProductAdapter adapter = new BasketProductAdapter(this, basket.getProducts(), basket.getProductsNumber());
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

        contentLayout.addView(view);

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

    private void populateBasket(){
        UserModel user = new UserModel("email", "1", "Georgi");
        ArrayList<ReviewModel> reviews = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        reviews.add(new ReviewModel(1, "excelent","Cel mai bun produs ever!!!!!", user, date, 5));
        reviews.add(new ReviewModel(2, "excelent","Un produs reussit", user, date, 5));
        reviews.add(new ReviewModel(3, "prost","Un produs mai prost de atat nu am vazut niciodata.....niciodata", user, date, 1));
        reviews.add(new ReviewModel(4, "dezamagitor","Ma asteptam la mai mult cu asemenea specificatii", user, date, 2));
        reviews.add(new ReviewModel(5, "se putea si mai bine","Merge, dar....", user, date, 4));
        reviews.add(new ReviewModel(6, "dragut, dar nu prea","", user, date, 2));
        reviews.add(new ReviewModel(7, "excelent","Sunt foate multumita de acest produs. Mi-a schimbat viata radical", user, date, 5));
        reviews.add(new ReviewModel(8, "alta intrebare?","Deci?", user, date, 4));
        reviews.add(new ReviewModel(9, "asa si asa","Eeeeee, oricum in vara imi iau altul", user, date, 3));


        //Laptop,Tablete,Telefoane

        //Laptop si accesorii

        //Laptop
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_e3aa490ff1062047b57a6a7a390fd55a_450x450_6dgt.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_77729d6b6b964f2c48594b8ba819d6fa_full.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_71a422b6d75aef41d1dd5ce3ff9545a6_450x450_rvr5.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_de3fc42584f675f47fb8b56745d3a6bd_450x450_4bkq.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_8dd379b097e24df71827927215cde99a_450x450_ppg3.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_a5abaabbe5ad6b0fe59452d275676b76_450x450_b47l.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_c91bc19926b35773112885a65b465078_450x450_r9iu.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_98f5a2c1fbf08604ac8f03e7e566147d_450x450_t9qe.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_6e75432b089311efd7ed1f0f801ff7d3_450x450_66nn.jpg");

        products.add(new Product(1, "Laptop ASUS X550VX-XX017D", " Procesor Intel® Core™ i7-6700HQ 2.60GHz, Skylake™, 15.6\", 8GB, 256GB SSD, nVIDIA GeForce GTX 950M 2GB, Free DOS",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 4249.99f, 30, "eMAG", 24, 21, reviews));

        /*images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_3a83f8272f26ec540a88dfa793433611_450x450_bovb.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_1db05558a11e8452620bf30b39ad5fc9_450x450_c458.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_fa8c0561479f2d595afc3f6f29ee32bb_450x450_qmep.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_936df95eb114c361bef10d968523e697_450x450_4856.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_225d7d3d0c883f86280bd3f9a14f26a6_450x450_heee.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_6a422db87a46b91deb827ba08e07701d_450x450_12vm.jpg");

        products.add(new Product(2, "Laptop ASUS X540SA-XX311", " Procesor Intel® Celeron® N3060 1.60GHz, Braswell, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 400, Free DOS, Chocolate Black",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 1666.0f, 36, "Biasicom", 24, 4, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/res_49e87252f4e6ede1b47eadc2aee9010b_450x450_5efn.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_2_450x450_ivj2.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_3_450x450_cek4.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_4_450x450_1cpd.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140413_1_full.jpg");

        products.add(new Product(3, "Laptop Apple MacBook Air 13", " Procesor Intel® Dual Core™ i5 1.60GHz, 13.3\", 8GB, 128GB SSD, Intel® HD Graphics 6000, OS X El Capitan, RO KB",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 5399.0f, 16, "eMAG", 24, 40, reviews));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2b43c469c6887c15da92248e20f2421b_450x450_pgh6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_12a3ebbe33a33bd567b628ed24772b7d_450x450_fbu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2e66698fd6d7830c1a35e7b77be4e358_450x450_7jvm.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_c1c90de37fa55ce61dfa0559a7ef0b12_450x450_seu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_46d953466630dcbc0359deccd5ebdc83_450x450_ttcr.jpg");

        products.add(new Product(7, "Laptop Gaming Acer Aspire F5-573G-707G", "Procesor  Intel® Core™ i7-7500U 2.70 GHz, Kaby Lake™, 15.6\", Full HD, 8GB, 256GB SSD, DVD-RW, nVIDIA GeForce GTX 950M 4GB, Linux, Silver",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 4742.39f, 36, "Neoplaza", 24, 14, reviews));*/

        ArrayList<Integer> number = new ArrayList<>();
        number.add(1);
       // number.add(1);
      //  number.add(2);
       // number.add(1);

        basket = new ShoppingBasket(1, user, products, number);
    }
}
