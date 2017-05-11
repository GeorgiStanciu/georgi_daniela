package com.example.georgi.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;

import java.util.ArrayList;

/**
 * Created by Georgi on 11-May-17.
 */

public class ProductOfOrderAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Product> products;
    private ArrayList<Integer> productNumber;
    public ProductOfOrderAdapter(Context context, ArrayList<Product> products, ArrayList<Integer> productNumber) {
        super(context, R.layout.product_order_layout, products);
        this.context = context;
        this.products = products;
        this.productNumber = productNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_order_layout, null);
        TextView name = (TextView) view.findViewById(R.id.product_name_order);
        TextView seller = (TextView) view.findViewById(R.id.product_seller_order);
        TextView number = (TextView) view.findViewById(R.id.number_of_product_order);
        TextView cost = (TextView) view.findViewById(R.id.product_cost_order);

        Product product = products.get(position);
        name.setText(product.getName());
        seller.setText("Vandut de: " + product.getSeller());
        number.setText("In numar de: " + productNumber.get(position));
        cost.setText("Subtotal: " + String.format("%.2f", (product.getPrice() * productNumber.get(position))));
        return view;
    }
}
