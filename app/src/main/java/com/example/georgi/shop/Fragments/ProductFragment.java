package com.example.georgi.shop.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.shop.Adapters.ProductImageAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnReviewAdded;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.FavoriteProducts;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

/**
 * Created by Georgi on 05-May-17.
 */

public class ProductFragment extends Fragment {

    private String title;
    private int page;
    private Product product;
    private RatingBar productRating;
    private TextView reviewNumber;
    private boolean isFavorite;

    public static ProductFragment newInstance(int page, String title, Product product, boolean isFavorite){
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putSerializable("product", product);
        args.putBoolean("isFavorite", isFavorite);
        productFragment.setArguments(args);
        return productFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
        product = (Product) getArguments().getSerializable("product");
        isFavorite = getArguments().getBoolean("isFavorite");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_view_fragment, container, false);

        ImageView mainImage = (ImageView) view.findViewById(R.id.product_main_image);
        RecyclerView picturesView = (RecyclerView) view.findViewById(R.id.product_image_list);
        final ImageView addToFavorite = (ImageView) view.findViewById(R.id.add_product_to_favorite);
        TextView oldPrice = (TextView) view.findViewById(R.id.product_old_price);
        TextView price = (TextView) view.findViewById(R.id.product_current_price);
        TextView outOfStockText = (TextView) view.findViewById(R.id.out_of_stock_message);
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        Picasso.with(getContext())
                .load(product.getImages().get(0))
                .into(mainImage);
        productRating = (RatingBar) view.findViewById(R.id.product_rating);
        reviewNumber = (TextView) view.findViewById(R.id.product_review_number);
        TextView stockState = (TextView) view.findViewById(R.id.product_stock_state);
        TextView seller = (TextView) view.findViewById(R.id.product_seller);
        TextView guarantee = (TextView) view.findViewById(R.id.product_guarantee);
        TextView description = (TextView) view.findViewById(R.id.product_description);
        RelativeLayout discountLayout = (RelativeLayout) view.findViewById(R.id.discount_ribbon_view_product);
        TextView discountText = (TextView) view.findViewById(R.id.discount_text_view_product);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        picturesView.setLayoutManager(layoutManager);

        ProductImageAdapter adapter = new ProductImageAdapter(getContext(), product.getImages());
        picturesView.setAdapter(adapter);

        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (product.getDiscount() == 0) {
            discountLayout.setVisibility(View.GONE);
            oldPrice.setVisibility(View.INVISIBLE);
            price.setText(String.format("%.2f", product.getPrice()) + " Lei");
        } else {
            discountLayout.setVisibility(View.VISIBLE);
            discountLayout.bringToFront();
            discountText.setText(product.getDiscount() + "\n %");
            float newPrice = product.getPrice() - product.getPrice() * product.getDiscount() / 100;
            oldPrice.setText(String.format("%.2f", product.getPrice()) + " Lei");
            price.setText(String.format("%.2f", newPrice) + " Lei");
        }

        productName.setText(product.getName());
        description.setText(product.getDescription());
        productRating.setRating(product.getRating());
        if (product.getRating() == 0.0f || product.getReviews() == null) {
            reviewNumber.setText("Fii primul care adauga un review.");
        } else {
            if (product.getReviews().size() == 1)
                reviewNumber.setText(product.getReviews().size() + " review");
            else
                reviewNumber.setText(product.getReviews().size() + " review-uri");
        }

        if (product.getQuantity() == 0) {
            outOfStockText.setVisibility(View.VISIBLE);
            stockState.setText("Stoc epuizat");
        } else if (product.getQuantity() < 6) {
            stockState.setText("Stoc limitat");
        } else
            stockState.setText("In stoc");


        seller.setText("Vandut de: " + product.getSeller());
        if (product.getGuarantee() == 0) {
            guarantee.setVisibility(View.GONE);
        } else {
            guarantee.setText("Garantie " + product.getGuarantee() + " luni");
        }

        if (isFavorite) {
            addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart));
            addToFavorite.setTag("heart");
        } else {
            addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart_outline));
            addToFavorite.setTag("heart_outline");
        }
        addToFavorite.setColorFilter(getResources().getColor(R.color.cherry), PorterDuff.Mode.SRC_IN);

        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
                int userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
                if (userId == 0) {
                    Toast.makeText(getContext(), "You must login to add products to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isFavorite) {
                        addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart));
                        addToFavorite.setTag("heart");
                        isFavorite = true;
                        new ChangeFavorite("add", userId).execute();

                    } else {
                        addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart_outline));
                        addToFavorite.setTag("heart_outline");
                        isFavorite = false;
                        new ChangeFavorite("remove", userId).execute();

                    }
                    addToFavorite.setColorFilter(getResources().getColor(R.color.cherry), PorterDuff.Mode.SRC_IN);
                }

            }
        });
        return view;
    }

    @Subscribe
    public void onRatingUpdate(OnReviewAdded event){

        productRating.setRating(event.getRating());
        if(event.getCount() == 1)
            reviewNumber.setText(event.getCount() +  " review");
        else
            reviewNumber.setText(event.getCount() +  " review-uri");
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        GlobalBus.getBus().unregister(this);
    }

    class ChangeFavorite extends AsyncTask{

        int userId;
        String operation;
        ChangeFavorite( String operation, int userId){
            this.operation = operation;
            this.userId = userId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand,userId));
            FavoriteProducts favoriteProducts = new FavoriteProducts((UserModel) response.getResponse(), product);
            if(operation.equals("add"))
                client.receiveDataFromServer(new Command(CommandEnum.AddFavoriteCommand, favoriteProducts));
            else
                client.receiveDataFromServer(new Command(CommandEnum.RemoveFavoriteCommand, favoriteProducts));
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));
            return null;
        }
    }

}
