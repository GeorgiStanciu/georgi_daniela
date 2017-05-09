package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.georgi.shop.Activities.ViewProductActivity;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeletedBasket;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Georgi on 09-May-17.
 */

public class BasketProductAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Product> products;
    private ArrayList<Integer> productsCount;
    public BasketProductAdapter(Context context, ArrayList<Product> products, ArrayList<Integer> productsCount) {
        super(context, R.layout.product_shop_basket, products);
        this.context = context;
        this.products = products;
        this.productsCount = productsCount;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_shop_basket, null);

        final Product product = products.get(position);
        RelativeLayout discountLayout = (RelativeLayout) view.findViewById(R.id.shop_discount_ribbon);
        TextView discountText = (TextView) view.findViewById(R.id.shop_discount_text);
        final ImageView imageView = (ImageView) view.findViewById(R.id.shop_product_image);
        Spinner itemCount = (Spinner) view.findViewById(R.id.shop_product_spinner);
        TextView oldPrice = (TextView) view.findViewById(R.id.shop_product_old_price);
        TextView price = (TextView) view.findViewById(R.id.shop_product_price);
        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        TextView name = (TextView) view.findViewById(R.id.shop_product_name);
        final ImageView menu = (ImageView) view.findViewById(R.id.shop_menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, menu);
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()){
                                    case R.id.delete_from_basket:
                                        GlobalBus.getBus().post(new OnProductDeletedBasket(position));
                                        products.remove(product);
                                        notifyDataSetChanged();
                                        return true;
                                    case R.id.add_to_favorite_shop:
                                        return true;
                                }
                                return false;
                            }
                        }
                );
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.shop_menu, popup.getMenu());
                popup.show();
            }
        });


        name.setText(product.getName());
        if(product.getImages() != null && product.getImages().size() != 0){
            Picasso.with(context)
                    .load(product.getImages().get(0))
                    .into(imageView);
        }
        if(product.getDiscount() == 0){
            discountLayout.setVisibility(View.GONE);
            oldPrice.setVisibility(View.INVISIBLE);
            price.setText(String.format("%.2f", product.getPrice()) + " Lei");
        }
        else{
            discountLayout.setVisibility(View.VISIBLE);
            discountText.setText(product.getDiscount() + "\n %");
            oldPrice.setText(String.format("%.2f", product.getPrice()) + " Lei");
            float newPrice = product.getPrice() - product .getPrice() * product.getDiscount() / 100;
            price.setText(String.format("%.2f", newPrice) + " Lei");
        }

        itemCount.setSelection(productsCount.get(position) - 1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("product",product);
                ((Activity) context).startActivity(intent);
            }
        });

        return view;
    }
}
