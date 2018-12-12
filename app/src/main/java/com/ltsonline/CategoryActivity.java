package com.ltsonline;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ltsonline.adapter.CategoryAdapter;
import com.ltsonline.adapter.ViewPagerAdapter;
import com.ltsonline.custom.SpacesItemDecoration;
import com.ltsonline.model.CategoryModel;
import com.ltsonline.model.GeneralViewpager;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    ArrayList<GeneralViewpager> viewpager_data = new ArrayList<>();
    ViewPagerAdapter viewpager_adapter;

    ArrayList<CategoryModel> categoryData = new ArrayList<>();
    CategoryAdapter adapter;
    @BindView(R.id.nestedScrollview)
    NestedScrollView nestedScrollview;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        recycleview.setNestedScrollingEnabled(false);

        try {
            Intent intent = getIntent();
            GlobalElements.setActionBar(CategoryActivity.this, "" + intent.getStringExtra("category_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewpager_data.clear();
        for (int b = 0; b < 5; b++) {
            GeneralViewpager da = new GeneralViewpager();
            da.setId(b);
            da.setUrl("https://www.eff.org/files/2015/03/02/eff-og-3.png");
            viewpager_data.add(da);
        }
        viewpager_adapter = new ViewPagerAdapter(CategoryActivity.this, viewpager_data);
        mainViewPager.setAdapter(viewpager_adapter);
        indicator.setViewPager(mainViewPager);

        CategoryModel da = new CategoryModel();
        da.setId(1);
        da.setName("Bed Set");
        da.setImage_path("");
        categoryData.add(da);
        da = new CategoryModel();
        da.setId(1);
        da.setName("Wardrobes & Cabinets");
        da.setImage_path("");
        categoryData.add(da);
        da = new CategoryModel();
        da.setId(1);
        da.setName("Mattresses");
        da.setImage_path("");
        categoryData.add(da);
        da = new CategoryModel();
        da.setId(1);
        da.setName("Dining set");
        da.setImage_path("");
        categoryData.add(da);
        da = new CategoryModel();
        da.setId(1);
        da.setName("Home & Office table");
        da.setImage_path("");
        categoryData.add(da);
        da = new CategoryModel();
        da.setId(1);
        da.setName("Metal Almirahs");
        da.setImage_path("");
        categoryData.add(da);

        for (int b = 0; b < 3; b++) {
            da = new CategoryModel();
            da.setId(1);
            da.setName("Bed Set");
            da.setImage_path("");
            categoryData.add(da);
            da = new CategoryModel();
            da.setId(1);
            da.setName("Wardrobes & Cabinets");
            da.setImage_path("");
            categoryData.add(da);
            da = new CategoryModel();
            da.setId(1);
            da.setName("Mattresses");
            da.setImage_path("");
            categoryData.add(da);
            da = new CategoryModel();
            da.setId(1);
            da.setName("Dining set");
            da.setImage_path("");
            categoryData.add(da);
            da = new CategoryModel();
            da.setId(1);
            da.setName("Home & Office table");
            da.setImage_path("");
            categoryData.add(da);
            da = new CategoryModel();
            da.setId(1);
            da.setName("Metal Almirahs");
            da.setImage_path("");
            categoryData.add(da);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
        int spanCount = 3; // 3 columns
        int spacing = 3; // 50px
        boolean includeEdge = false;
        recycleview.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
        adapter = new CategoryAdapter(CategoryActivity.this, categoryData);
        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(layoutManager);
        recycleview.setFocusable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) CategoryActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(CategoryActivity.this.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(CategoryActivity.this, R.drawable.ic_search_black_24dp));
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));
        searchEditText.setHint("Search ...");
        ImageView searchMagIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchMagIcon.setImageResource(R.drawable.ic_close_black_24dp);
        return super.onCreateOptionsMenu(menu);
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
