package com.example.georgi.shop.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("1", "tv sahjsashajshajshajshajshajshajshjashajshajsha ", "", 2.3f));
        products.add(new Product("2", "mobile", "", 4.3f));
        products.add(new Product("3", "book", "", 5.65f));
        products.add(new Product("4", "table", "", 32.72f));
        products.add(new Product("5", "laptop", "", 5.65f));
        products.add(new Product("5", "mouse", "", 32.72f));

        ProductAdapter adapter = new ProductAdapter(this, products);
        gridView.setAdapter(adapter);
    }
}
