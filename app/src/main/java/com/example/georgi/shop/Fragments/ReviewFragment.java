package com.example.georgi.shop.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.georgi.shop.R;

/**
 * Created by Georgi on 05-May-17.
 */

public class ReviewFragment extends Fragment {

    private String title;
    private int page;


    public static ReviewFragment newInstance(int page, String title){
        ReviewFragment reviewFragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        reviewFragment.setArguments(args);
        return reviewFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.product_rating_review);
        ratingBar.setRating(4.4f);
        return view;
    }
}