package com.example.georgi.shop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.georgi.shop.Adapters.ReviewAdapter;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;

import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Georgi on 05-May-17.
 */

public class ReviewFragment extends Fragment {

    private String title;
    private int page;
    private ArrayList<ReviewModel> reviews;

    public static ReviewFragment newInstance(int page, String title, ArrayList<ReviewModel> reviews){
        ReviewFragment reviewFragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putSerializable("reviews", reviews);
        reviewFragment.setArguments(args);
        return reviewFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
        reviews = (ArrayList<ReviewModel>) getArguments().getSerializable("reviews");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.review_fragment, container, false);
        LinearLayout reviewsLayout = (LinearLayout) view.findViewById(R.id.reviews_layout);
        RelativeLayout noReviewsLayout = (RelativeLayout) view.findViewById(R.id.no_reviews_layout);
        if(reviews != null && reviews.size() > 0) {
            reviewsLayout.setVisibility(View.VISIBLE);
            noReviewsLayout.setVisibility(View.GONE);


            TextView productQualifying = (TextView) view.findViewById(R.id.product_qualifying);
            TextView addReview = (TextView) view.findViewById(R.id.add_review_text);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.product_rating_review);
            ProgressBar progressFive = (ProgressBar) view.findViewById(R.id.five_stars_progress);
            TextView textFive = (TextView) view.findViewById(R.id.five_stars_count);
            ProgressBar progressFour = (ProgressBar) view.findViewById(R.id.four_stars_progress);
            TextView textFour = (TextView) view.findViewById(R.id.four_stars_count);
            ProgressBar progressThree = (ProgressBar) view.findViewById(R.id.three_stars_progress);
            TextView textThree = (TextView) view.findViewById(R.id.three_stars_count);
            ProgressBar progressTwo = (ProgressBar) view.findViewById(R.id.two_stars_progress);
            TextView textTwo = (TextView) view.findViewById(R.id.two_stars_count);
            ProgressBar progressOne = (ProgressBar) view.findViewById(R.id.one_stars_progress);
            TextView textOne = (TextView) view.findViewById(R.id.one_stars_count);
            GridView gridView = (GridView) view.findViewById(R.id.review_grid);


            int five = 0, four = 0, three = 0, two = 0, one = 0;
            float rating = 0;
            for (ReviewModel review : reviews) {
                rating += review.getQualifying();
                if (review.getQualifying() == 5)
                    five++;
                else if (review.getQualifying() == 4)
                    four++;
                else if (review.getQualifying() == 3)
                    three++;
                else if (review.getQualifying() == 2)
                    two++;
                else
                    one++;
            }
            rating /= reviews.size();
            productQualifying.setText(String.format("%.1f", rating));
            ratingBar.setRating(rating);
            progressFive.setMax(reviews.size());
            progressFour.setMax(reviews.size());
            progressThree.setMax(reviews.size());
            progressTwo.setMax(reviews.size());
            progressOne.setMax(reviews.size());
            progressFive.setProgress(five);
            progressFour.setProgress(four);
            progressThree.setProgress(three);
            progressTwo.setProgress(two);
            progressOne.setProgress(one);
            textFive.setText(five + "");
            textFour.setText(four + "");
            textThree.setText(three + "");
            textTwo.setText(two + "");
            textOne.setText(one + "");

            ReviewAdapter adapter = new ReviewAdapter(getContext(), reviews);
            gridView.setAdapter(adapter);

            addReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addReview();
                }
            });

        }
        else{
            reviewsLayout.setVisibility(View.GONE);
            noReviewsLayout.setVisibility(View.VISIBLE);
            Button addReview = (Button) view.findViewById(R.id.add_review_button_emplty_review);
            addReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addReview();
                }
            });
        }
        return view;
    }


    private void addReview(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_review_layout, null);
        builder.setView(view);
        ImageView closeButton = (ImageView) view.findViewById(R.id.close_add_review);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.add_review_rating);
        final TextView title = (TextView) view.findViewById(R.id.add_review_title);
        final TextView content = (TextView) view.findViewById(R.id.add_review_content);
        TextView send = (TextView) view.findViewById(R.id.add_review_send);
        final TextView noRating = (TextView) view.findViewById(R.id.no_rating_set_add_review);
        final TextView noContent = (TextView) view.findViewById(R.id.no_content_set_add_review);

        final AlertDialog dialog = builder.create();
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rating = (int) ratingBar.getRating();
                String reviewTitle = title.getText().toString();
                String reviewContent = content.getText().toString();
                Date date = new Date();
                UserModel user = new UserModel();

                if(rating == 0){
                    noRating.setVisibility(View.VISIBLE);
                }
                else{
                    noRating.setVisibility(View.GONE);
                }
                if(reviewContent.equals("")){
                    noContent.setVisibility(View.VISIBLE);
                }
                else{
                    noContent.setVisibility(View.GONE);
                }
                if(rating != 0 && !reviewContent.equals("")) {
                    ReviewModel newReview = new ReviewModel(reviewTitle, reviewContent, user, date, rating);
                    dialog.dismiss();
                }
            }
        });
    }
}