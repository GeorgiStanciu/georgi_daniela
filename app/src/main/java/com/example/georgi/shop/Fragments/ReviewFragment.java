package com.example.georgi.shop.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.georgi.shop.Adapters.ReviewAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnReviewAdded;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.sql.Date;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Georgi on 05-May-17.
 */

public class ReviewFragment extends Fragment {

    private String title;
    private int page;
    private ArrayList<ReviewModel> reviews;
    private ReviewAdapter adapter;
    private int productId;
    private RatingBar ratingBar;
    private ProgressBar progressFive;
    private TextView textFive;
    private ProgressBar progressFour;
    private TextView textFour;
    private ProgressBar progressThree;
    private TextView textThree;
    private ProgressBar progressTwo;
    private TextView textTwo;
    private ProgressBar progressOne;
    private TextView textOne;
    private LinearLayout reviewsLayout;
    private RelativeLayout noReviewsLayout;
    private TextView productQualifying;

    public static ReviewFragment newInstance(int page, String title, ArrayList<ReviewModel> reviews, int productId){
        ReviewFragment reviewFragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putSerializable("reviews", reviews);
        args.putInt("productId",productId);
        reviewFragment.setArguments(args);
        return reviewFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
        reviews = (ArrayList<ReviewModel>) getArguments().getSerializable("reviews");
        productId = getArguments().getInt("productId");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.review_fragment, container, false);
        reviewsLayout = (LinearLayout) view.findViewById(R.id.reviews_layout);
        noReviewsLayout = (RelativeLayout) view.findViewById(R.id.no_reviews_layout);

        productQualifying = (TextView) view.findViewById(R.id.product_qualifying);
        TextView addReview = (TextView) view.findViewById(R.id.add_review_text);
        ratingBar = (RatingBar) view.findViewById(R.id.product_rating_review);
        progressFive = (ProgressBar) view.findViewById(R.id.five_stars_progress);
        textFive = (TextView) view.findViewById(R.id.five_stars_count);
        progressFour = (ProgressBar) view.findViewById(R.id.four_stars_progress);
        textFour = (TextView) view.findViewById(R.id.four_stars_count);
        progressThree = (ProgressBar) view.findViewById(R.id.three_stars_progress);
        textThree = (TextView) view.findViewById(R.id.three_stars_count);
        progressTwo = (ProgressBar) view.findViewById(R.id.two_stars_progress);
        textTwo = (TextView) view.findViewById(R.id.two_stars_count);
        progressOne = (ProgressBar) view.findViewById(R.id.one_stars_progress);
        textOne = (TextView) view.findViewById(R.id.one_stars_count);
        GridView gridView = (GridView) view.findViewById(R.id.review_grid);

        adapter = new ReviewAdapter(getContext(), reviews);
        gridView.setAdapter(adapter);

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReview();
            }
        });


        if(reviews != null && reviews.size() > 0) {
            reviewsLayout.setVisibility(View.VISIBLE);
            noReviewsLayout.setVisibility(View.GONE);


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

        }
        else{
            reviewsLayout.setVisibility(View.GONE);
            noReviewsLayout.setVisibility(View.VISIBLE);
            Button addNewReview = (Button) view.findViewById(R.id.add_review_button_emplty_review);
            addNewReview.setOnClickListener(new View.OnClickListener() {
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
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
                int userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
                if(userId == 0){
                    Toast.makeText(getContext(), "You must be connected to add review", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
                else {
                    int rating = (int) ratingBar.getRating();
                    String reviewTitle = title.getText().toString();
                    String reviewContent = content.getText().toString();
                    java.util.Date date = new java.util.Date();
                    Date sqlDate = new Date(date.getTime());


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
                        ReviewModel newReview = new ReviewModel(reviewTitle, reviewContent, null, sqlDate, rating, productId);
                        if (reviews == null)
                            reviews = new ArrayList<ReviewModel>();
                       // reviews.add(newReview);
                      //  adapter.notifyDataSetChanged();
                        new SendCommand(newReview, userId).execute();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    private void addReviewDone(ReviewModel review){

        reviews.add(review);
        adapter.notifyDataSetChanged();

        if(reviews.size() != 1) {
            float newRating = (ratingBar.getRating() * (reviews.size() - 1) + review.getQualifying()) / reviews.size();
            ratingBar.setRating(newRating);
            productQualifying.setText(String.format("%.1f", newRating));
            GlobalBus.getBus().post(new OnReviewAdded(newRating, reviews.size()));

        }

        else{

            noReviewsLayout.setVisibility(View.GONE);
            reviewsLayout.setVisibility(View.VISIBLE);
            ratingBar.setRating(review.getQualifying());
            productQualifying.setText(String.format("%.1f", (float)review.getQualifying()));

            GlobalBus.getBus().post(new OnReviewAdded((float)review.getQualifying(), 1));


        }

        progressFive.setMax(reviews.size());
        progressFour.setMax(reviews.size());
        progressThree.setMax(reviews.size());
        progressTwo.setMax(reviews.size());
        progressOne.setMax(reviews.size());

        if (review.getQualifying() == 5) {
            progressFive.setProgress(progressFive.getProgress() + 1);
            textFive.setText(progressFive.getProgress() + "");
        } else if (review.getQualifying() == 4) {
            progressFour.setProgress(progressFour.getProgress() + 1);
            textFour.setText(progressFour.getProgress() + "");
        } else if (review.getQualifying() == 3) {
            progressThree.setProgress(progressThree.getProgress() + 1);
            textThree.setText(progressThree.getProgress() + "");
        } else if (review.getQualifying() == 2) {
            progressTwo.setProgress(progressTwo.getProgress() + 1);
            textTwo.setText(progressTwo.getProgress() + "");
        } else {
            progressOne.setProgress(progressOne.getProgress() + 1);
            textOne.setText(progressOne.getProgress() + "");
        }
    }

    class SendCommand extends AsyncTask{

        private ReviewModel review;
        private int userId;
        SendCommand(ReviewModel review, int userId){
            this.review = review;
            this.userId = userId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client =  new Client();
            client.connectToServer();
            CommandResponse user = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand,userId));
            review.setUser((UserModel) user.getResponse());
            Client client1 = new Client();
            client1.connectToServer();
            CommandResponse response = client1.receiveDataFromServer(new Command(CommandEnum.AddReviewCommand, review));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            addReviewDone(review);
        }
    }


}