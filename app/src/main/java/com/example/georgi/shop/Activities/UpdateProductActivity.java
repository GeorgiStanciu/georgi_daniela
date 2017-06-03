package com.example.georgi.shop.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.shop.Adapters.ProductImageAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;

public class UpdateProductActivity extends BaseActivity {

    private Product product;
    private ProductImageAdapter adapter;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_update_product, null);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
        RecyclerView images = (RecyclerView) view.findViewById(R.id.product_image_list);

        final TextView productName = (TextView) view.findViewById(R.id.product_name);
        final TextView productDescription = (TextView) view.findViewById(R.id.product_description);
        final TextView productPrice = (TextView) view.findViewById(R.id.product_price);
        final TextView productDiscount = (TextView) view.findViewById(R.id.product_discount);
        final TextView productSeller = (TextView) view.findViewById(R.id.product_seller);
        final TextView productGuarantee = (TextView) view.findViewById(R.id.product_guarantee);
        final TextView productQuantity = (TextView) view.findViewById(R.id.product_quantity);
        final TextView productCategory = (TextView) view.findViewById(R.id.product_category);

        Button add = (Button) view.findViewById(R.id.add_product);

        productCategory.setText(product.getCategory());
        productDescription.setText(product.getDescription());
        productName.setText(product.getName());
        productPrice.setText(product.getPrice() + "");
        productDiscount.setText(product.getDiscount() + "");
        productGuarantee.setText(product.getGuarantee() + "");
        productSeller.setText(product.getSeller());
        productQuantity.setText(product.getQuantity() + "");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        images.setLayoutManager(layoutManager);

        adapter = new ProductImageAdapter(this, product.getImages());
        images.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = productDescription.getText().toString();
                float price = -1;
                if(!productPrice.getText().toString().equals(""))
                    price = Float.parseFloat(productPrice.getText().toString());
                int discount = -1;
                if(!productDiscount.getText().toString().equals(""))
                    discount = Integer.parseInt(productDiscount.getText().toString());
                int guarantee = - 1;
                if(!productGuarantee.getText().toString().equals(""))
                    guarantee = Integer.parseInt(productGuarantee.getText().toString());
                int quantity = -1;
                if(!productQuantity.getText().toString().equals(""))
                    quantity = Integer.parseInt(productQuantity.getText().toString());
                String seller = productSeller.getText().toString();
                if( description.equals("") || seller.equals("") || price == -1 || discount == -1
                        || guarantee == -1 || quantity == -1 || product.getImages().size() == 0){
                    Toast.makeText(UpdateProductActivity.this, "Complite all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    product.setDescription(description);
                    product.setPrice(price);
                    product.setDiscount(discount);
                    product.setGuarantee(guarantee);
                    product.setQuantity(quantity);
                    product.setSeller(seller);

                    new UpdateProduct().execute();
                }
            }
        });
        contentLayout.addView(view);

    }
    class UpdateProduct extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            client.receiveDataFromServer(new Command(CommandEnum.UpdateProductCommand, product));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(UpdateProductActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateProductActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        }
    }
}
