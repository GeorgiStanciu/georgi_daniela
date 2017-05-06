package com.example.georgi.shop.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.product_rating_review);
        ratingBar.setRating(4.2f);

        return view;
    }
}