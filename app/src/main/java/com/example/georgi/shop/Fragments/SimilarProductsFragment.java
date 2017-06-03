package com.example.georgi.shop.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;

/**
 * Created by Georgi on 05-May-17.
 */


public class SimilarProductsFragment extends Fragment {

    private String title;
    private int page;
    private String category;
    private ArrayList<Product> products;
    private ProductAdapter adapter;
    private GridView gridView;
    private ProgressBar progressBar;

    public static SimilarProductsFragment newInstance(int page, String title, String category){
        SimilarProductsFragment similarProductsFragment = new SimilarProductsFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putString("category", category);
        similarProductsFragment.setArguments(args);

        return similarProductsFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
        title = getArguments().getString("title");
        category = getArguments().getString("category");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.similar_products_fragment, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view_similar_products);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
        products = new ArrayList<>();
        new LoadProducts().execute();
        adapter = new ProductAdapter(getContext(), products, R.menu.product_menu,false);
        gridView.setAdapter(adapter);
        return view;
    }

    class LoadProducts extends AsyncTask{


        @Override
        protected Object doInBackground(Object[] objects) {
            Client client =  new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetProductByCategoryCommand, category));
            products = (ArrayList<Product>) response.getResponse();
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            adapter.setProducts(products);

        }
    }

}