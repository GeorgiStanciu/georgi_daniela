package com.example.georgi.shop.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
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

public class AddProductActivity extends BaseActivity {

    private Product product;
    private ProductImageAdapter adapter;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_add_product, null);

        TextView addImage = (TextView) view.findViewById(R.id.add_image_product);
        RecyclerView images = (RecyclerView) view.findViewById(R.id.product_image_list);

        final TextView productName = (TextView) view.findViewById(R.id.product_name);
        final TextView productDescription = (TextView) view.findViewById(R.id.product_description);
        final TextView productPrice = (TextView) view.findViewById(R.id.product_price);
        final TextView productDiscount = (TextView) view.findViewById(R.id.product_discount);
        final TextView productSeller = (TextView) view.findViewById(R.id.product_seller);
        final TextView productGuarantee = (TextView) view.findViewById(R.id.product_guarantee);
        final TextView productQuantity = (TextView) view.findViewById(R.id.product_quantity);
        final Spinner productCategory = (Spinner) view.findViewById(R.id.product_category);

        Button add = (Button) view.findViewById(R.id.add_product);

        product = new Product();
        product.setImages(new ArrayList<String>());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        images.setLayoutManager(layoutManager);

        adapter = new ProductImageAdapter(this, product.getImages());
        images.setAdapter(adapter);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            String message = "Select image";
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(galleryIntent, message),1);

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString();
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
                String category = (String) productCategory.getSelectedItem();
                if(name.equals("") || description.equals("") || seller.equals("") || price == -1 || discount == -1
                        || guarantee == -1 || quantity == -1 || product.getImages().size() == 0){
                    Toast.makeText(AddProductActivity.this, "Complite all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    product.setName(name);
                    product.setDescription(description);
                    product.setPrice(price);
                    product.setDiscount(discount);
                    product.setGuarantee(guarantee);
                    product.setQuantity(quantity);
                    product.setCategory(category);
                    product.setSeller(seller);

                    new AddProduct().execute();
                }
            }
        });
        contentLayout.addView(view);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Uri uri = data.getData();
            product.getImages().add(uri.toString());
            adapter.notifyDataSetChanged();
        }
    }

    class AddProduct extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            client.receiveDataFromServer(new Command(CommandEnum.AddProductCommand, product));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(AddProductActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        }
    }
}