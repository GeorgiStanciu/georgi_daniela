package com.example.georgi.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Georgi on 08-May-17.
 */

public class ReviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ReviewModel> reviews;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviews){

        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
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
        View cardView = inflater.inflate(R.layout.review_card, null);

        ReviewModel review = reviews.get(position);
        RatingBar ratingBar = (RatingBar) cardView.findViewById(R.id.review_rating_card);
        TextView dateClient = (TextView) cardView.findViewById(R.id.review_date_and_client);
        TextView title = (TextView) cardView.findViewById(R.id.review_title);
        TextView description = (TextView) cardView.findViewById(R.id.review_description);

        ratingBar.setRating(review.getQualifying());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(review.getDate());
        dateClient.setText(dateString + " de "+ review.getUser().getName());
        title.setText(review.getTitle());
        description.setText(review.getReview());
        return cardView;
    }
}
