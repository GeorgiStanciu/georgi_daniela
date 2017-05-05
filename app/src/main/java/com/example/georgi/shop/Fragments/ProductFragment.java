package com.example.georgi.shop.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georgi.shop.R;

/**
 * Created by Georgi on 05-May-17.
 */

public class ProductFragment extends Fragment {

    private String title;
    private int page;


    public static ProductFragment newInstance(int page, String title){
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        productFragment.setArguments(args);
        return productFragment;
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
        View view = inflater.inflate(R.layout.product_view_fragment, container, false);
        return view;
    }
}
