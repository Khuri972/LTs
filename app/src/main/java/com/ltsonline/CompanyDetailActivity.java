package com.ltsonline;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.custom.FontSource;
import com.ltsonline.model.CompanyModel;
import com.ltsonline.model.GeneralModel;
import com.ltsonline.pageAdapter.CompanyPagerAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyDetailActivity extends AppCompatActivity {

    CompanyModel companyModel = new CompanyModel();
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    ArrayList<GeneralModel> data = new ArrayList<>();
    CompanyPagerAdapter pagerAdapter;

    @BindView(R.id.seller_load_more)
    TextView sellerLoadMore;
    @BindView(R.id.company_img)
    ImageView companyImg;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.company_email)
    TextView companyEmail;
    @BindView(R.id.company_mobile)
    TextView companyMobile;
    @BindView(R.id.company_address)
    TextView companyAddress;
    @BindView(R.id.list_inquiry_main)
    LinearLayout listInquiryMain;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.comapany_tab)
    TabLayout comapanyTab;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.content)
    CoordinatorLayout content;/*
    @BindView(R.id.view_pager)
    ViewPager viewPager;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_company_detail);
        ButterKnife.bind(this);

        GlobalElements.viewPager=(ViewPager)findViewById(R.id.view_pager);

        try {
            Bundle bundle = getIntent().getExtras();
            companyModel = (CompanyModel) bundle.getSerializable("data");
            GlobalElements.setActionBar(CompanyDetailActivity.this, companyModel.getName());
            GlobalElements.overrideFonts_P_Nova_Rg_Regular(CompanyDetailActivity.this, listInquiryMain);
            companyName.setTypeface(FontSource.process(this,R.raw.proxima_nova_bold));
            companyName.setText("" + companyModel.getName());
            companyEmail.setText("" + companyModel.getEmail());
            companyMobile.setText("" + companyModel.getMobile());
            companyAddress.setText("" + companyModel.getAddress());
            imageLoader.init(ImageLoaderConfiguration.createDefault(CompanyDetailActivity.this));
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.splash_logo)
                    .showImageOnFail(R.drawable.splash_logo)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            imageLoader.displayImage(companyModel.getImage_path(), companyImg, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoadPageAdapter();
    }

    public void LoadPageAdapter() {
        GeneralModel da = new GeneralModel();
        da.setId("1");
        da.setTitle("Company Detail");
        data.add(da);

        da = new GeneralModel();
        da.setId("2");
        da.setTitle("Post");
        data.add(da);

        da = new GeneralModel();
        da.setId("3");
        da.setTitle("Product");
        data.add(da);

        pagerAdapter = new CompanyPagerAdapter(this.getSupportFragmentManager(), data);
        GlobalElements.viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        comapanyTab.setupWithViewPager(GlobalElements.viewPager);

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
