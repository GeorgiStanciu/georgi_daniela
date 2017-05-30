package com.example.georgi.shop.Activities;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.georgi.shop.Adapters.CategoryAdapter;
import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.Category;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;

public class FindProductActivity extends BaseActivity {

    private ArrayList<Category> categories;
    private ListView category;
    private ArrayList<Product> products;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_find_product,null);

        category = (ListView) view.findViewById(R.id.find_product_by_category);
        String parentCategory = getIntent().getStringExtra("category");
        new FindByCategory(parentCategory).execute();
        contentLayout.addView(view);
    }

    private void setCategories(){

        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        category.setAdapter(adapter);
    }

    private void setProducts(){

        ProductAdapter adapter= new ProductAdapter(this, products, R.menu.product_menu);
        category.setAdapter(adapter);
    }


    class FindByCategory extends AsyncTask{

        String parent;
        FindByCategory(String parent){
            this.parent = parent;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.ViewCategoriesCommandByParent, parent));
            categories = (ArrayList<Category>) response.getResponse();
            if(parent.equals("Shop")){
                for(Category category: categories){
                    if(category.getImage().equals("table"))
                        category.setIcon(R.mipmap.ic_tablet);
                    else if(category.getImage().equals("mouse"))
                        category.setIcon(R.mipmap.ic_mouse_variant);
                    else if(category.getImage().equals("camera"))
                        category.setIcon(R.mipmap.ic_camera);
                    else if(category.getImage().equals("washing_machine"))
                        category.setIcon(R.mipmap.ic_washing_machine);
                    else if(category.getImage().equals("tshirt"))
                        category.setIcon(R.mipmap.ic_tshirt);
                    else if(category.getImage().equals("face"))
                        category.setIcon(R.mipmap.ic_face);
                    else if(category.getImage().equals("gift"))
                        category.setIcon(R.mipmap.ic_gift);
                    else if(category.getImage().equals("sofa"))
                        category.setIcon(R.mipmap.ic_sofa);
                    else if(category.getImage().equals("tennis"))
                        category.setIcon(R.mipmap.ic_tennis);
                    else if(category.getImage().equals("car"))
                        category.setIcon(R.mipmap.ic_car);
                    else
                        category.setIcon(R.mipmap.ic_baby_buggy);
                }
            }
            if(categories == null || categories.size() == 0){
                CommandResponse responseParent = client.receiveDataFromServer(new Command(CommandEnum.GetProductByCategoryCommand, parent));
                products = (ArrayList<Product>) responseParent.getResponse();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(products == null)
                setCategories();
            else
                setProducts();
        }
    }
}
