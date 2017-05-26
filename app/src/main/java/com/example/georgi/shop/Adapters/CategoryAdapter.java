package com.example.georgi.shop.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgi.shop.Activities.FindProductActivity;
import com.example.georgi.shop.Models.Category;
import com.example.georgi.shop.R;

import java.util.ArrayList;

/**
 * Created by Georgi on 10-May-17.
 */

public class CategoryAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, R.layout.category_layout, categories);
        this.categories = categories;
        this.context = context;
    }


    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_layout, null);
        ImageView icon = (ImageView) view.findViewById(R.id.category_icon);
        TextView name = (TextView) view.findViewById(R.id.category_name);
        final Category category = categories.get(position);

        if(category.getImage() == null || category.getImage().equals("")){
            icon.setVisibility(View.GONE);
        }
        else{
            icon.setImageDrawable(context.getResources().getDrawable(category.getIcon()));
        }
        name.setText(category.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FindProductActivity.class);
                intent.putExtra("category", category.getName());
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }
}
