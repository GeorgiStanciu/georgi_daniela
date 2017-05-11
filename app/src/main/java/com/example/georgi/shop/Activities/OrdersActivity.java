package com.example.georgi.shop.Activities;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.georgi.shop.Adapters.OrdersAdapter;
import com.example.georgi.shop.Models.OrderModel;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class OrdersActivity extends BaseActivity {


    private ArrayList<OrderModel> orders;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_orders,null);

        RelativeLayout emptyLayout = (RelativeLayout) view.findViewById(R.id.empty_orders_layout);
        ListView ordersList = (ListView) view.findViewById(R.id.ordered_products_list);

        populateOrder();
        if(orders == null || orders.size() == 0){
            emptyLayout.setVisibility(View.VISIBLE);
            ordersList.setVisibility(View.GONE);
            Button orderButton = (Button) view.findViewById(R.id.empty_orders_button);
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OrdersActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }
            });
        }
        else{
            OrdersAdapter adapter = new OrdersAdapter(this, orders);
            ordersList.setAdapter(adapter);
        }
        contentLayout.addView(view);
    }

    private void populateOrder(){

        orders = new ArrayList<>();
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


        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> productNumber = new ArrayList<>();

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

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_3a83f8272f26ec540a88dfa793433611_450x450_bovb.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_1db05558a11e8452620bf30b39ad5fc9_450x450_c458.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_fa8c0561479f2d595afc3f6f29ee32bb_450x450_qmep.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_936df95eb114c361bef10d968523e697_450x450_4856.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_225d7d3d0c883f86280bd3f9a14f26a6_450x450_heee.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_6a422db87a46b91deb827ba08e07701d_450x450_12vm.jpg");

        products.add(new Product(2, "Laptop ASUS X540SA-XX311", " Procesor Intel® Celeron® N3060 1.60GHz, Braswell, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 400, Free DOS, Chocolate Black",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 1666.0f, 36, "Biasicom", 24, 4, null));


        productNumber.add(1);
        productNumber.add(1);
        orders.add(new OrderModel(1, user, products, productNumber, new Date(), 6554.32f));


        products = new ArrayList<>();
       productNumber = new ArrayList<>();
        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/res_49e87252f4e6ede1b47eadc2aee9010b_450x450_5efn.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_2_450x450_ivj2.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_3_450x450_cek4.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_4_450x450_1cpd.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140413_1_full.jpg");

        products.add(new Product(3, "Laptop Apple MacBook Air 13", " Procesor Intel® Dual Core™ i5 1.60GHz, 13.3\", 8GB, 128GB SSD, Intel® HD Graphics 6000, OS X El Capitan, RO KB",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 5399.0f, 16, "eMAG", 24, 40, reviews));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_6406644063198bf893accc1b2f71b694_450x450_qi87.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_728caf0dc991cd01079160abe128af4b_450x450_5kac.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_77fdcc4b35713ad8eb7cfc6e0af35041_450x450_tu76.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_1b65075e0d3708f6a96dfb0b073776eb_450x450_tqc1.jpg");

        products.add(new Product(4, "Laptop HP 250 G5", " Procesor Intel® Core ™ i3-5005U 2.00GHz, Broadwell™, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 5500, Free DOS, Black",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 1699.99f, 0, "eMAG", 24, 0, reviews));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_56f5c18cc2b6a45d293b6c4a24d5e4a9_450x450_mj2u.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_f78a9381197197abf6a4d7dfd12488ef_450x450_fv8i.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_ffb300eaee2c0314d0d14b1d882abdec_450x450_dod6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_b28a94c0bdb74bd6bbe615acf7dfcc91_450x450_riqi.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_87c9671ff94bee432c1a4bbbee029d1a_450x450_nl2n.jpg");

        products.add(new Product(5, "Laptop DELL 15.6'' Vostro 3568 (seria 3000), HD", " Procesor Intel® Core™ i3-6100U (3M Cache, 2.30 GHz), 4GB DDR4, 1TB, GMA HD 520, Linux, Black",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 1990.99f, 0, "Neoplaza", 24, 4, reviews));


        productNumber.add(1);
        productNumber.add(2);
        productNumber.add(1);
        orders.add(new OrderModel(2, user, products, productNumber, new Date(), 13549.72f));


    }
}
