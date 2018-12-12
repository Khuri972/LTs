package com.ltsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ltsonline.adapter.CompanyAdapter;
import com.ltsonline.model.CompanyModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyActivity extends AppCompatActivity {

    ArrayList<CompanyModel> data = new ArrayList<>();
    CompanyAdapter adapter;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_company);
        ButterKnife.bind(this);

        try {
            Intent intent = getIntent();
            GlobalElements.setActionBar(CompanyActivity.this, "" + intent.getStringExtra("category_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            CompanyModel da = new CompanyModel();
            da.setId("1");
            da.setName("Lts Online");
            da.setMobile("9898989898");
            da.setEmail("lts@gmail.com");
            da.setAddress("Rajkot 360005");
            if (i == 0) {
                da.setImage_path("https://brandongaille.com/wp-content/uploads/2013/07/Adidas-Company-Logo.jpg");
            } else if (i == 1) {
                da.setImage_path("http://logok.org/wp-content/uploads/2014/04/Apple-Logo-black.png");
            } else if (i == 2) {
                da.setImage_path("https://1000logos.net/wp-content/uploads/2017/05/Symbole-Reebok.jpg");
            } else if (i == 3) {
                da.setImage_path("https://www.zerobulb.in/wp-content/uploads/2017/01/Corporate-Logo-Designing-for-Dubai-Client.jpg");
            } else if (i == 4) {
                da.setImage_path("https://www.designfreelogoonline.com/wp-content/uploads/2016/03/00106-3D-company-logo-design-free-logo-online-Template-03.png");
            } else if (i == 5) {
                da.setImage_path("https://cdn.dribbble.com/users/623927/screenshots/2619722/shot-56-wedgegaming.png");
            } else if (i == 6) {
                da.setImage_path("https://www.thedesignlove.com/wp-content/uploads/2016/11/Famous-Logos-Designed-in-Circle-Shape-yamaha-1024x750.jpg");
            } else if (i == 7) {
                da.setImage_path("https://www.fixpocket.com/public_assets/uploads/beats/1523422664maxresdefault.jpg");
            } else if (i == 8) {
                da.setImage_path("http://www.logorium.com/sites/default/files/images/14196/logos/creative%20hand%20logo_1.jpg");
            } else if (i == 9) {
                da.setImage_path("http://www.dermaleleganceskincareclinic.co.uk/uploads/2/4/4/1/24415521/1514456_orig.png");
            } else {
                da.setImage_path("");
            }
            data.add(da);
        }
        adapter = new CompanyAdapter(CompanyActivity.this, data);
        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(new LinearLayoutManager(CompanyActivity.this, LinearLayoutManager.VERTICAL, false));
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
