package com.example.georgi.shop.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.georgi.shop.Adapters.CategoryAdapter;
import com.example.georgi.shop.Models.Category;
import com.example.georgi.shop.R;

import java.util.ArrayList;

public class FindProductActivity extends BaseActivity {

    private ArrayList<Category> categories;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_find_product,null);

        ListView category = (ListView) view.findViewById(R.id.find_product_by_category);
        String parentCategory = getIntent().getStringExtra("category");
        if(parentCategory == null || parentCategory.equals(""))
            populateCategory();
        else
            categories = new ArrayList<>();
        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        category.setAdapter(adapter);
        contentLayout.addView(view);
    }

    private void populateCategory(){
        categories = new ArrayList<>();
        categories.add(new Category(1, "Laptop, Tablete & Telefoane", "", R.mipmap.ic_tablet));
        categories.add(new Category(2, "PC, Periferice & Software", "", R.mipmap.ic_mouse_variant));
        categories.add(new Category(3, "TV, Audio, Foto & Gamming", "", R.mipmap.ic_camera));
        categories.add(new Category(4, "Elenctrocasnice & Climatizare", "", R.mipmap.ic_washing_machine));
        categories.add(new Category(5, "Fashion", "", R.mipmap.ic_tshirt));
        categories.add(new Category(6, "Ingrijire Personala & Cosmetice", "", R.mipmap.ic_face));
        categories.add(new Category(7, "Carti, Birotica & Cadouri", "", R.mipmap.ic_gift));
        categories.add(new Category(8, "Casa, Bricolaj & Petshop", "", R.mipmap.ic_sofa));
        categories.add(new Category(9, "Sport & Activitati in aer liber", "", R.mipmap.ic_tennis));
        categories.add(new Category(10, "Auto, Moto & RCA", "", R.mipmap.ic_car));
        categories.add(new Category(11, "Jucarii, Copii & Bebe", "", R.mipmap.ic_baby_buggy));

    }
}
