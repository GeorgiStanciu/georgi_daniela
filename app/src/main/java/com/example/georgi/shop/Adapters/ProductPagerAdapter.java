package com.example.georgi.shop.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.georgi.shop.Fragments.ProductFragment;
import com.example.georgi.shop.Fragments.ReviewFragment;
import com.example.georgi.shop.Fragments.SimilarProductsFragment;

/**
 * Created by Georgi on 05-May-17.
 */

public class ProductPagerAdapter extends FragmentPagerAdapter {

    private final  int numItmes = 3;
    public ProductPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ProductFragment.newInstance(0, "Product");
            case 1:
                return ReviewFragment.newInstance(1, "Review");
            case 2:
                return SimilarProductsFragment.newInstance(2, "Similar Products");
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
