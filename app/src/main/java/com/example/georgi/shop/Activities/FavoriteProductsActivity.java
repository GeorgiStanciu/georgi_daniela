package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FavoriteProductsActivity extends BaseActivity {

    private FavoriteProducts favorite;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_favorite_products,null);
        RelativeLayout emptyFavorite = (RelativeLayout) view.findViewById(R.id.empty_favorite_layout);
        GridView gridView = (GridView) view.findViewById(R.id.favorite_products_grid);

        final SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        final String userId = sharedPreferences.getString(getString(R.string.user_id_preference), "");
        populateProducts();

        if(userId.equals("") || favorite.getProducts() == null || favorite.getProducts().size() == 0){
            emptyFavorite.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);

            TextView emptyText = (TextView) view.findViewById(R.id.message_empty_favorite);
            Button emptyButton = (Button) view.findViewById(R.id.empty_favorite_button);
            if(userId.equals("")){
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
            else{
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
        }

        else{
            emptyFavorite.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            ProductAdapter adapter = new ProductAdapter(this, favorite.getProducts());
            gridView.setAdapter(adapter);
        }
        contentLayout.addView(view);
    }

    private void populateProducts() {
        favorite = new FavoriteProducts();
        UserModel user = new UserModel("email", "1", "Georgi");
        ArrayList<ReviewModel> reviews = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        reviews.add(new ReviewModel(1, "excelent", "Cel mai bun produs ever!!!!!", user, date, 5));
        reviews.add(new ReviewModel(2, "excelent", "Un produs reussit", user, date, 5));
        reviews.add(new ReviewModel(3, "prost", "Un produs mai prost de atat nu am vazut niciodata.....niciodata", user, date, 1));
        reviews.add(new ReviewModel(4, "dezamagitor", "Ma asteptam la mai mult cu asemenea specificatii", user, date, 2));
        reviews.add(new ReviewModel(5, "se putea si mai bine", "Merge, dar....", user, date, 4));
        reviews.add(new ReviewModel(6, "dragut, dar nu prea", "", user, date, 2));
        reviews.add(new ReviewModel(7, "excelent", "Sunt foate multumita de acest produs. Mi-a schimbat viata radical", user, date, 5));
        reviews.add(new ReviewModel(8, "alta intrebare?", "Deci?", user, date, 4));
        reviews.add(new ReviewModel(9, "asa si asa", "Eeeeee, oricum in vara imi iau altul", user, date, 3));


        //Laptop,Tablete,Telefoane

        //Laptop si accesorii

        //Laptop
        ArrayList<Product> products;
        products = new ArrayList<>();
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


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_4db21274133ffa1316bc6c4ac9e1f462_full.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_1e252e276225542d59a1a9bdf197819c_450x450_j5ti.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_a9f9b7a7228897be8009b3f0148f8f75_450x450_ur1b.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_8458a5c2d74243f4aa9b7926c0bc9195_450x450_qcaf.jpg");

        products.add(new Product(6, "Laptop 2in1 Odys Fusion 12", "11.6 inch IPS LED FHD, 4Core Intel Atom X5 1.44 GHz, 2GB + 32GB, Wi-Fi, Bluetooth, Micro HDMI, Windows 10 Home, Tastatura, Negru (Include: Tastatura tip Dock)",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 1399.0f, 21, "Atu IT", 24, 0, reviews));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2b43c469c6887c15da92248e20f2421b_450x450_pgh6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_12a3ebbe33a33bd567b628ed24772b7d_450x450_fbu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2e66698fd6d7830c1a35e7b77be4e358_450x450_7jvm.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_c1c90de37fa55ce61dfa0559a7ef0b12_450x450_seu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_46d953466630dcbc0359deccd5ebdc83_450x450_ttcr.jpg");

        products.add(new Product(7, "Laptop Gaming Acer Aspire F5-573G-707G", "Procesor  Intel® Core™ i7-7500U 2.70 GHz, Kaby Lake™, 15.6\", Full HD, 8GB, 256GB SSD, DVD-RW, nVIDIA GeForce GTX 950M 4GB, Linux, Silver",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 4742.39f, 36, "Neoplaza", 24, 14, reviews));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_4bb700667298951f03d3b1f029de2c42_450x450_5s3k.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_c2c593d8999c374cee3f03019d01b7b9_450x450_b6dr.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_93c5c5bc209d1098ff39940ec173c2e7_450x450_h2k8.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_7b1dffc94275a5d5c227a1e99ee67e6a_450x450_sbag.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_d106281ae2eff5f8a2e6052338574590_450x450_hs6g.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_db6c8245331a46d30012e68effde5228_450x450_2ejb.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_15f2c675bd9cabc92198f2b44660f828_450x450_dh7e.jpg");

        products.add(new Product(8, "Laptop Lenovo IdeaPad 710S P-13IKB", " Procesor Intel® Core™ i5-7200U 2.50 GHz Kaby Lake™, 13.3\" Full HD IPS, 8GB, 512GB SSD, nVIDIA® GeForce® 940M 2GB,Microsoft Windows 10 Home, Silver",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 4699.99f, 14, "eMAG", 24, 26, reviews));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_7e8c99504ed6044f83bba808bf3b4cda_450x450_l5o4.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_10071b8bf822e860ad104036c7f1ecdb_450x450_n0aj.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_9ade8b90316578a104dfb04255d8334e_450x450_308.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_dc58d1499aadd4efb044b5606de7f116_450x450_de6o.jpg");

        products.add(new Product(9, "Ultrabook Dell XPS 13 9360", " Procesor Intel® Core™ i7-7500U 2.70GHz, Kaby Lake™, 13.3\", FHD, 8GB, 256GB SSD, Intel® HD Graphics 620, Microsoft Windows 10 Home, Silver",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 8330.000f, 0, "Alien Store", 24, 45, reviews));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_05a1217973a2b4517b95874159aab378_450x450_82aj.jpg");
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_47b24f5f54619556b9d936cec239ec7e_450x450_euko.jpg");
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_23dc5d62d107a2fcbfa212ccc6dd8963_450x450_2r63.jpg");

        products.add(new Product(10, "Laptop Apple MacBook 12", " Procesor  Intel® Dual Core™ M5 1.20GHz, 12\", Ecran Retina, 8GB, 512GB SSD, Intel® HD Graphics 515, OS X El Capitan, RO KB, Rose Gold",
                images, "Laptop,Tablete, Telefoane/Laptop si accesorii/Laptop", 8399.0f, 10, "eMag", 24, 13, reviews));


        favorite.setProducts(products);
    }
    }
