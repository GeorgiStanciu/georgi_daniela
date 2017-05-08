package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgi.shop.Activities.ViewProductActivity;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Georgi on 02-May-17.
 */

public class ProductAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }
    @Override
    public int getCount() {
        if(products != null)
           return products.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View cardView = inflater.inflate(R.layout.item_card, null);

        TextView name = (TextView) cardView.findViewById(R.id.product_name);
        final TextView price = (TextView) cardView.findViewById(R.id.product_price);
        TextView oldPrice = (TextView) cardView.findViewById(R.id.product_old_price);
        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView image = (ImageView) cardView.findViewById(R.id.product_image);
        final Product product = products.get(position);
        name.setText(product.getName());
        price.setText(String.format("%.2f", product.getPrice()) + " Lei");

        if(product.getImages() != null && product.getImages().size() > 0){
            Picasso.with(context)
                    .load(product.getImages().get(0))
                    .into(image);
        }
        if(product.getDiscount() != 0){
            oldPrice.setVisibility(View.VISIBLE);
            oldPrice.setText(String.format("%.2f", product.getPrice()) + " Lei");
            float newPrice = product.getPrice() - product .getPrice() * product.getDiscount() / 100;
            price.setText(String.format("%.2f", newPrice) + " Lei");
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("product",product);
                ((Activity) context).startActivity(intent);
            }
        });

        return cardView;
    }
}
