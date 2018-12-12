package com.ltsonline;

import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.adapter.CartAdapter;
import com.ltsonline.adapter.HomeAdapter;
import com.ltsonline.custom.ItemOffsetDecoration;
import com.ltsonline.custom.RecyclerViewPositionHelper;
import com.ltsonline.model.HomeModel;
import com.ltsonline.model.ProductModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SellerProfileActivity extends AppCompatActivity {

    @BindView(R.id.tlUserProfileTabs)
    TabLayout tlUserProfileTabs;
    @BindView(R.id.rvUserProfile)
    RecyclerView recyclerView;
    @BindView(R.id.content)
    CoordinatorLayout layout;
    @BindView(R.id.seller_load_more)
    TextView load_more;
    @BindView(R.id.seller_name)
    TextView seller_name;
    @BindView(R.id.seller_profile_status)
    TextView profile_status;
    @BindView(R.id.seller_feeds_count)
    TextView feed_count;
    @BindView(R.id.seller_product_count)
    TextView product_count;
    @BindView(R.id.seller_follower_count)
    TextView following_count;
    @BindView(R.id.seller_following)
    Button seller_following;
    @BindView(R.id.seller_image)
    CircleImageView image;

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    ArrayList<HomeModel> models = new ArrayList<>();
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    int firstVisibleItem, visibleItemCount, totalItemCount, firstVisibleItem_feed, visibleItemCount_feed, totalItemCount_feed, count = 8, count_feed = 8;
    protected int m_PreviousTotalCount, m_PreviousTotalCount_feed;
    RecyclerViewPositionHelper mRecyclerViewHelper;

    HomeAdapter itemListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_seller_profile);
        ButterKnife.bind(this);
        GlobalElements.setActionBar(SellerProfileActivity.this, "Seller Detail");

        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_grid_on_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_feed_white));

        int tabIconColor0 = ContextCompat.getColor(SellerProfileActivity.this, R.color.black);
        int tabIconColor = ContextCompat.getColor(SellerProfileActivity.this, R.color.gray);
        tlUserProfileTabs.getTabAt(0).getIcon().setColorFilter(tabIconColor0, PorterDuff.Mode.SRC_IN);
        tlUserProfileTabs.getTabAt(1).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        tlUserProfileTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(SellerProfileActivity.this, R.color.black);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                if (tab.getPosition() == 0) {

                    //Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_LONG).show();
                   /* recyclerView.setHasFixedSize(true);
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                    recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                    ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(SellerProfileActivity.this, R.dimen.padding);
                    recyclerView.addItemDecoration(itemDecoration);
                    itemListDataAdapter = new HomeAdapter(SellerProfileActivity.this, models);
                    recyclerView.setAdapter(itemListDataAdapter);*/
                } else if (tab.getPosition() == 1) {
                    /*recyclerView.setHasFixedSize(true);
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                    recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                    ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(SellerProfileActivity.this, R.dimen.padding);
                    recyclerView.addItemDecoration(itemDecoration);
                    itemListDataAdapter = new HomeAdapter(SellerProfileActivity.this, models);
                    recyclerView.setAdapter(itemListDataAdapter);*/
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(SellerProfileActivity.this, R.color.gray);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        loadSellerProfile();

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

    public void loadSellerProfile() {
        HomeModel da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://i.pinimg.com/originals/54/cf/fc/54cffc04c843c9517be22822230123c0.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-18-21611.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-10-21603.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        itemListDataAdapter = new HomeAdapter(SellerProfileActivity.this, models);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(itemListDataAdapter);
        layout.setVisibility(View.VISIBLE);
    }
}
