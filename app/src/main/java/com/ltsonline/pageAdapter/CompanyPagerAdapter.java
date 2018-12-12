package com.ltsonline.pageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.ltsonline.fragment.CompanyDetailFragment;
import com.ltsonline.fragment.PostFragment;
import com.ltsonline.fragment.ProductFragment;
import com.ltsonline.model.GeneralModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CRAFT BOX on 12/23/2016.
 */

public class CompanyPagerAdapter extends FragmentPagerAdapter {
    Fragment f;
    List<Fragment> mFragmentList = new ArrayList<>();
    ArrayList<GeneralModel> categoryModels = new ArrayList<>();

    public CompanyPagerAdapter(FragmentManager fm, ArrayList<GeneralModel> topCategoryModels) {
        super(fm);
        this.categoryModels = topCategoryModels;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryModels.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return categoryModels.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            f = new CompanyDetailFragment();
            return f;
        } else if (position == 1) {
            f = new PostFragment();
            return f;
        } else {
            f = new ProductFragment();
            return f;
        }
    }
}
