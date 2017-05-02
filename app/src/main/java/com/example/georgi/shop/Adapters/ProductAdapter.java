package com.example.georgi.shop.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;

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
        return products.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.item_card, null);

        TextView name = (TextView) cardView.findViewById(R.id.product_name);
        TextView price = (TextView) cardView.findViewById(R.id.product_price);
        TextView oldPrice = (TextView) cardView.findViewById(R.id.product_old_price);
        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView image = (ImageView) cardView.findViewById(R.id.product_image);

        Product product = products.get(position);
        name.setText(product.getName());
        price.setText(product.getPrice() + "");
        oldPrice.setText(product.getPrice() + "");


        return cardView;
    }
}
