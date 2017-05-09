package com.example.georgi.shop.Fragments;

import android.graphics.Paint;
import android.graphics.PorterDuff;
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

import com.example.georgi.shop.Adapters.ProductImageAdapter;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Georgi on 05-May-17.
 */

public class ProductFragment extends Fragment {

    private String title;
    private int page;
    private Product product;


    public static ProductFragment newInstance(int page, String title, Product product){
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putSerializable("product", product);
        productFragment.setArguments(args);
        return productFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
        product = (Product) getArguments().getSerializable("product");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_view_fragment, container, false);

        product.setRating(4.2f);



        ImageView mainImage = (ImageView) view.findViewById(R.id.product_main_image);
        RecyclerView picturesView = (RecyclerView) view.findViewById(R.id.product_image_list);
        final ImageView addToFavorite =  (ImageView) view.findViewById(R.id.add_product_to_favorite);
        TextView oldPrice = (TextView) view.findViewById(R.id.product_old_price);
        TextView price = (TextView) view.findViewById(R.id.product_current_price);
        TextView outOfStockText = (TextView) view.findViewById(R.id.out_of_stock_message);
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        Picasso.with(getContext())
                .load(product.getImages().get(0))
                .into(mainImage);
        RatingBar productRating = (RatingBar) view.findViewById(R.id.product_rating);
        TextView reviewNumber = (TextView) view.findViewById(R.id.product_review_number);
        TextView stockState = (TextView) view.findViewById(R.id.product_stock_state);
        TextView seller = (TextView) view.findViewById(R.id.product_seller);
        TextView guarantee = (TextView) view.findViewById(R.id.product_guarantee);
        TextView description = (TextView) view.findViewById(R.id.product_description);
        RelativeLayout discountLayout  = (RelativeLayout) view.findViewById(R.id.discount_ribbon_view_product);
        TextView discountText = (TextView) view.findViewById(R.id.discount_text_view_product);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        picturesView.setLayoutManager(layoutManager);

        ProductImageAdapter adapter = new ProductImageAdapter(getContext(), product.getImages());
        picturesView.setAdapter(adapter);

        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if(product.getDiscount() == 0) {
            discountLayout.setVisibility(View.GONE);
            oldPrice.setVisibility(View.INVISIBLE);
            price.setText(String.format("%.2f", product.getPrice()) + " Lei");
        }
        else{
            discountLayout.setVisibility(View.VISIBLE);
            discountLayout.bringToFront();
            discountText.setText(product.getDiscount() + "\n %");
            float newPrice = product.getPrice() - product .getPrice() * product.getDiscount() / 100;
            oldPrice.setText(String.format("%.2f", product.getPrice()) + " Lei");
            price.setText(String.format("%.2f", newPrice) + " Lei");
        }

        productName.setText(product.getName());
        description.setText(product.getDescription());
        productRating.setRating(product.getRating());
        if(product.getRating() == 0.0f || product.getReviews() == null){
            reviewNumber.setText("Fii primul care adauga un review.");
        }
        else{
            if(product.getReviews().size() == 1)
                reviewNumber.setText(product.getReviews().size() +  " review");
            else
                reviewNumber.setText(product.getReviews().size() +  " review-uri");
        }

        if(product.getQuantity() == 0){
            outOfStockText.setVisibility(View.VISIBLE);
            stockState.setText("Stoc epuizat");
        }
        else if(product.getQuantity() < 6){
            stockState.setText("Stoc limitat");
        }
        else
            stockState.setText("In stoc");


        seller.setText("Vandut de: " + product.getSeller());
        if(product.getGuarantee() == 0){
            guarantee.setVisibility(View.GONE);
        }
        else{
            guarantee.setText("Garantie " + product.getGuarantee() + " luni");
        }

        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addToFavorite.getTag() != null && addToFavorite.getTag().toString().equals("heart_outline")){
                    addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart));
                    addToFavorite.setTag("heart");

                }
                else{
                    addToFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.ic_heart_outline));
                    addToFavorite.setTag("heart_outline");

                }
                  addToFavorite.setColorFilter(getResources().getColor(R.color.cherry), PorterDuff.Mode.SRC_IN);

            }
        });
        return view;
    }
}
