package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Georgi on 06.05.2017.
 */

public class ProductImageAdapter extends RecyclerView.Adapter<ProductImageAdapter.PictureHolder>{


    private Context context;
    private ArrayList<String> images;

    public ProductImageAdapter(Context context, ArrayList<String> images){
        this.context = context;
        this.images = images;
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.product_image_card, parent, false);
        PictureHolder holder = new PictureHolder(contactView);
        return holder;
    }

    @Override
    public void onBindViewHolder(PictureHolder holder, int position) {
        final String image = images.get(position);
        Picasso.with(context)
                .load(image)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView mainImage = (ImageView) ((Activity) context).findViewById(R.id.product_main_image);
                Picasso.with(context)
                        .load(image)
                        .into(mainImage);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(images != null)
           return images.size();
        return  0;
    }

    class PictureHolder extends RecyclerView.ViewHolder{


        private ImageView image;

        public PictureHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.product_image_recycler);
        }
    }
}
