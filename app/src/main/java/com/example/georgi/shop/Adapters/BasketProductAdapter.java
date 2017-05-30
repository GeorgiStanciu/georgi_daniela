package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.georgi.shop.Activities.ViewProductActivity;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeleted;
import com.example.georgi.shop.Helpers.OnTotalSumChange;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ProductBasket;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Georgi on 09-May-17.
 */

public class BasketProductAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Product> products;
    private ArrayList<Integer> productsCount;
    private int userId;
    public BasketProductAdapter(Context context, ArrayList<Product> products, ArrayList<Integer> productsCount, int userId) {
        super(context, R.layout.product_shop_basket, products);
        this.context = context;
        this.products = products;
        this.productsCount = productsCount;
        this.userId = userId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.product_shop_basket, null);

        final Product product = products.get(position);
        RelativeLayout discountLayout = (RelativeLayout) view.findViewById(R.id.shop_discount_ribbon);
        TextView discountText = (TextView) view.findViewById(R.id.shop_discount_text);
        final ImageView imageView = (ImageView) view.findViewById(R.id.shop_product_image);
        Spinner itemCount = (Spinner) view.findViewById(R.id.shop_product_spinner);
        TextView oldPrice = (TextView) view.findViewById(R.id.shop_product_old_price);
        final TextView price = (TextView) view.findViewById(R.id.shop_product_price);
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
                                        GlobalBus.getBus().post(new OnProductDeleted(position));
                                        products.remove(product);
                                        notifyDataSetChanged();
                                        new ChangeProductBasket(product.getId(), "remove", 0).execute();
                                        return true;
                                    case R.id.add_to_favorite_shop:
                                        new AddFavorite(userId, product).execute();
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

        itemCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                int count = productsCount.get(position);
                int newCount =  Integer.parseInt(adapterView.getItemAtPosition(pos).toString().substring(0,1));
                productsCount.set(position, newCount);
                if(newCount != count) {
                    new ChangeProductBasket(product.getId(), "add", newCount - count).execute();
                    GlobalBus.getBus().post(new OnTotalSumChange());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("productId",product.getId());
                ((Activity) context).startActivity(intent);
            }
        });

        return view;
    }


    class ChangeProductBasket extends AsyncTask{

        int productId;
        String operation;
        int quantity;
        ChangeProductBasket( int productId, String operation, int quantity){
            this.operation = operation;
            this.productId = productId;
            this.quantity = quantity;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            ProductBasket productBasket = new ProductBasket(userId, productId, quantity);
            if(operation.equals("remove"))
                client.receiveDataFromServer(new Command(CommandEnum.RemoveProductFromBasketCommand, productBasket));
            else{
                client.receiveDataFromServer(new Command(CommandEnum.AddProductToBasketCommand, productBasket));
            }
            return null;
        }
    }

    class AddFavorite extends AsyncTask{

        int userId;
        Product product;
        AddFavorite( int userId, Product product){
            this.userId = userId;
            this.product = product;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand,userId));
            FavoriteProducts favoriteProducts = new FavoriteProducts((UserModel) response.getResponse(), product);
            client.receiveDataFromServer(new Command(CommandEnum.AddFavoriteCommand, favoriteProducts));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }
    }

}
