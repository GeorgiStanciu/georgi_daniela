package com.example.georgi.shop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georgi.shop.R;

/**
 * Created by Georgi on 05-May-17.
 */


public class SimilarProductsFragment extends Fragment {

    private String title;
    private int page;


    public static SimilarProductsFragment newInstance(int page, String title){
        SimilarProductsFragment similarProductsFragment = new SimilarProductsFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        similarProductsFragment.setArguments(args);
        return similarProductsFragment;
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
        View view = inflater.inflate(R.layout.similar_products_fragment, container, false);
        return view;
    }
}