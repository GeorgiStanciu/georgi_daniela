package com.example.georgi.shop.Activities;

import android.view.LayoutInflater;
import android.view.View;

import com.example.georgi.shop.R;

public class DeliverInfoActivity extends BaseActivity {


    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_deliver_info,null);

        contentLayout.addView(view);
    }
}
