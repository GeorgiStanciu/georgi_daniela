package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Activities.ViewProductActivity;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnProductDeleted;
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
 * Created by Georgi on 02-May-17.
 */

public class ProductAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private Context context;
    private int productMenu;

    public ProductAdapter(Context context, ArrayList<Product> products, int productMenu){
        this.context = context;
        this.products = products;
        this.productMenu = productMenu;
    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
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
        RelativeLayout discountLayout = (RelativeLayout) cardView.findViewById(R.id.discount_product_ribbon);
        TextView discountText = (TextView) cardView.findViewById(R.id.discount_product_text);
        final ImageView menu = (ImageView) cardView.findViewById(R.id.product_menu);
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference), Context.MODE_PRIVATE);
        final int userId = sharedPreferences.getInt(context.getString(R.string.user_id_preference), 0);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, menu);
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()){
                                    case R.id.add_to_basket:
                                        GlobalBus.getBus().post(new OnProductDeleted(position));
                                        new AddProductBasket(product.getId(), 0, userId).execute();
                                        return true;
                                    case R.id.add_to_favorite:
                                        new ChangeFavorite("add",userId,  product).execute();
                                        return true;
                                    case R.id.delete_from_favorite:
                                        new ChangeFavorite("remove",userId,  product).execute();
                                        products.remove(product);
                                        notifyDataSetChanged();
                                        GlobalBus.getBus().post(new OnProductDeleted(products.size()));
                                        return true;
                                }
                                return false;
                            }
                        }
                );
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(productMenu, popup.getMenu());
                popup.show();
            }
        });
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
            discountLayout.setVisibility(View.VISIBLE);
            discountText.setText(product.getDiscount() + "\n%");

        }
        else{
            discountLayout.setVisibility(View.GONE);
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("productId",product.getId());
                ((Activity) context).startActivity(intent);
            }
        });

        return cardView;
    }


    class AddProductBasket extends AsyncTask{

        int productId;
        int quantity;
        int userId;
        AddProductBasket( int productId, int quantity, int userId){
            this.productId = productId;
            this.quantity = quantity;
            this.userId = userId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            ProductBasket productBasket = new ProductBasket(userId, productId, quantity);
            client.receiveDataFromServer(new Command(CommandEnum.AddProductToBasketCommand, productBasket));

            return null;
        }
    }

    class ChangeFavorite extends AsyncTask {

        int userId;
        String operation;
        Product product;

        ChangeFavorite(String operation, int userId, Product product) {
            this.operation = operation;
            this.userId = userId;
            this.product = product;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand, userId));
            FavoriteProducts favoriteProducts = new FavoriteProducts((UserModel) response.getResponse(), product);
            if (operation.equals("add"))
                client.receiveDataFromServer(new Command(CommandEnum.AddFavoriteCommand, favoriteProducts));
            else
                client.receiveDataFromServer(new Command(CommandEnum.RemoveFavoriteCommand, favoriteProducts));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }
    }
}
