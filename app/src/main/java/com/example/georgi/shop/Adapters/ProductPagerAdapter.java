package com.example.georgi.shop.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.georgi.shop.Fragments.ProductFragment;
import com.example.georgi.shop.Fragments.ReviewFragment;
import com.example.georgi.shop.Fragments.SimilarProductsFragment;
import com.example.georgi.shop.Models.Product;

/**
 * Created by Georgi on 05-May-17.
 */

public class ProductPagerAdapter extends FragmentPagerAdapter {

    private final  int numItmes = 3;
    private Product product;
    public ProductPagerAdapter(FragmentManager fm, Product product) {
        super(fm);
        this.product = product;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ProductFragment.newInstance(0, "Product", product);
            case 1:
                return ReviewFragment.newInstance(1, "Review", product.getReviews(), product.getId());
            case 2:
                return SimilarProductsFragment.newInstance(2, "Similar Products", product.getCategory());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numItmes;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Product";
            case 1:
                return "Review";
            case 2:
                return "Similar Products";
        }
        return super.getPageTitle(position);
    }
}
