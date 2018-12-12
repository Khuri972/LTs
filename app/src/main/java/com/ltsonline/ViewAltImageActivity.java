package com.ltsonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ltsonline.adapter.ImagePagerAdapter;
import com.ltsonline.adapter.TouchImageAdapter;
import com.ltsonline.custom.ExtendedViewPager;
import com.ltsonline.model.GeneralViewpager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAltImageActivity extends AppCompatActivity {

    @BindView(R.id.extendedViewPager1)
    ExtendedViewPager mViewPager;
    //String position;
    ArrayList<GeneralViewpager> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_view_alt_image);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        //position=bundle.getString("position");
        data = (ArrayList<GeneralViewpager>) bundle.getSerializable("data");
        mViewPager=(ExtendedViewPager) findViewById(R.id.extendedViewPager1);
        mViewPager.setAdapter(new ImagePagerAdapter(ViewAltImageActivity.this,data));
        //mViewPager.setCurrentItem(Integer.parseInt(position));
    }
}
