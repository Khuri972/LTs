package com.ltsonline;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ltsonline.adapter.ProductDetailPagerAdapter;
import com.ltsonline.model.GeneralViewpager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class ImageViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    ArrayList<GeneralViewpager> data = new ArrayList<>();
    ProductDetailPagerAdapter viewpager_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_image_view_pager);
        ButterKnife.bind(this);
        GlobalElements.setActionBar(ImageViewPagerActivity.this, "Image");
        try {
            Bundle bundle = getIntent().getExtras();
            data = (ArrayList<GeneralViewpager>) bundle.getSerializable("data");
            int position = bundle.getInt("position");
            viewpager_adapter = new ProductDetailPagerAdapter(ImageViewPagerActivity.this, data);
            mainViewPager.setAdapter(viewpager_adapter);
            indicator.setViewPager(mainViewPager);
            mainViewPager.setCurrentItem(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
