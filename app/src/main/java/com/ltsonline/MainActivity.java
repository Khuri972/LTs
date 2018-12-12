package com.ltsonline;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ltsonline.adapter.MultiFunctionAdapter;
import com.ltsonline.adapter.HomeCategoryAdapter;
import com.ltsonline.adapter.ViewPagerAdapter;
import com.ltsonline.bottamCameraPick.ImagePickerDemo;
import com.ltsonline.dialog.PriceDialog;
import com.ltsonline.model.CategoryModel;
import com.ltsonline.model.HomeModel;
import com.ltsonline.model.HomeMultiModel;
import com.ltsonline.custom.WrapContentViewPager;
import com.ltsonline.model.GeneralViewpager;
import com.ltsonline.netUtils.MyPreferences;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_view_pager)
    WrapContentViewPager mainViewPager;

    @BindView(R.id.main_recycleview)
    RecyclerView mainRecycleview;

    @BindView(R.id.category_recycleview)
    RecyclerView categoryRecycleview;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    ArrayList<GeneralViewpager> viewpager_data = new ArrayList<>();
    ViewPagerAdapter viewpager_adapter;
    HomeCategoryAdapter homeCategoryAdapter;
    ArrayList<CategoryModel> data = new ArrayList<>();
    ArrayList<HomeMultiModel> homeModels = new ArrayList<>();
    MultiFunctionAdapter homeAdapter;
    MyPreferences myPreferences;


    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                MainActivity.this.getWindow().setStatusBarColor(Color.BLACK);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.background_color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myPreferences = new MyPreferences(this);
        Fabric.with(this, new Crashlytics());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        mainRecycleview.setNestedScrollingEnabled(false);
        categoryRecycleview.setNestedScrollingEnabled(false);

        /* g+ */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        /* */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //  toggle.setDrawerIndicatorEnabled(false);
        //toggle.setHomeAsUpIndicator(R.drawable.ic_camera_black_36dp);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        //  navigationView.getMenu().getItem(0).sett;

       /* try {
            int i = 4;
            TextView d = (TextView) findViewById(R.id.first_txt);
            d.setText("" + i);
        } catch (Exception e) {
            e.printStackTrace();
            Crashlytics.logException(e);
        }*/

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                try {
                    if (tabId == R.id.tab_search) {

                    } else if (tabId == R.id.tab_chat) {
                        Intent i = new Intent(MainActivity.this, ChatRoomActivity.class);
                        startActivityForResult(i, 0);
                    } else if (tabId == R.id.tab_camera) {
                        try {
                            Intent intent = new Intent(getApplicationContext(), ImagePickerDemo.class);
                            intent.putExtra("picker", "multi");
                            startActivityForResult(intent, 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (tabId == R.id.tab_home) {

                    } else if (tabId == R.id.tab_profile) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                try {
                    if (tabId == R.id.tab_search) {

                    } else if (tabId == R.id.tab_chat) {
                        Intent i = new Intent(MainActivity.this, ChatRoomActivity.class);
                        startActivityForResult(i, 0);
                    } else if (tabId == R.id.tab_camera) {
                        try {
                            Intent intent = new Intent(getApplicationContext(), ImagePickerDemo.class);
                            intent.putExtra("picker", "multi");
                            startActivityForResult(intent, 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (tabId == R.id.tab_home) {

                    } else if (tabId == R.id.tab_profile) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewpager_data.clear();
        for (int b = 0; b < 5; b++) {
            GeneralViewpager da = new GeneralViewpager();
            da.setId(b);
            if (b == 0) {
                da.setUrl("https://www.goair.in/media/3481/lp-563-x-275-student-ssr-02.jpg");
            } else if (b == 1) {
                da.setUrl("https://www.jio.com/mi5_jio_desktop_v1.jpg");
            } else if (b == 2) {
                da.setUrl("https://www.flashsaletricks.com/wp-content/uploads/2017/06/Jio_Offer_Samsung_Users_28June.png");
            } else {
                da.setUrl("https://images.via.com/static/dynimg/search_page/61/normal/1111485405-1111485404_airasia-banners---india-offer-pagejpg.jpg");
            }
            viewpager_data.add(da);
        }
        viewpager_adapter = new ViewPagerAdapter(MainActivity.this, viewpager_data);
        mainViewPager.setAdapter(viewpager_adapter);
        indicator.setViewPager(mainViewPager);

        CategoryModel da = new CategoryModel();
        da.setId(1);
        da.setName("Electronic");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Furniture");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Properties");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Jobs");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Cars");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Electronic");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Furniture");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Properties");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Jobs");
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(1);
        da.setName("Cars");
        da.setImage_path("");
        data.add(da);

        homeCategoryAdapter = new HomeCategoryAdapter(MainActivity.this, data);
        categoryRecycleview.setAdapter(homeCategoryAdapter);
        categoryRecycleview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));

        HomeMultiModel homeMultiModel = new HomeMultiModel();
        homeMultiModel.setType(1);

        ArrayList<HomeModel> models = new ArrayList<>();
        HomeModel da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/61UwrPEQZUL._SY355_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://assets.mspcdn.net/t_c-desktop-normal,f_auto,q_auto,d_c:noimage.jpg/c/11918-55-1.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);


        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://www.t-mobile.com/content/dam/t-mobile/en-p/cell-phones/samsung/samsung-galaxy-s9/lilac-purple/Samsung-Galaxy-S9-Lilac-Purple-1-3x.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/81BPF5NLr5L._SX425_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://i.ebayimg.com/images/g/N7QAAOSwHhRbx5cx/s-l300.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/411jXtQp3jL._SX425_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);
        homeMultiModel.setHomeModels(models);
        homeModels.add(homeMultiModel);

        homeMultiModel = new HomeMultiModel();
        homeMultiModel.setType(2);
        homeModels.add(homeMultiModel);

        homeMultiModel = new HomeMultiModel();
        homeMultiModel.setType(1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/61UwrPEQZUL._SY355_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://assets.mspcdn.net/t_c-desktop-normal,f_auto,q_auto,d_c:noimage.jpg/c/11918-55-1.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);


        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://www.t-mobile.com/content/dam/t-mobile/en-p/cell-phones/samsung/samsung-galaxy-s9/lilac-purple/Samsung-Galaxy-S9-Lilac-Purple-1-3x.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/81BPF5NLr5L._SX425_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://i.ebayimg.com/images/g/N7QAAOSwHhRbx5cx/s-l300.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://images-na.ssl-images-amazon.com/images/I/411jXtQp3jL._SX425_.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);
        homeMultiModel.setHomeModels(models);
        homeModels.add(homeMultiModel);

        homeAdapter = new MultiFunctionAdapter(MainActivity.this, homeModels);
        mainRecycleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainRecycleview.setAdapter(homeAdapter);
        mainRecycleview.setHasFixedSize(true);

        /*final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true);

        hud.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 5000);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (myPreferences.getPreferences(MyPreferences.id).equals("")) {
                navView.getMenu().getItem(9).setTitle("Login");
            } else {
                navView.getMenu().getItem(9).setTitle("Logout");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNavMenuItemThemeColors(int color) {
        //Setting default colors for menu item Text and Icon
        int navDefaultTextColor = Color.parseColor("#312517");
        int navDefaultIconColor = Color.parseColor("#312517");

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[]{
                        color,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor
                }
        );

        //Defining ColorStateList for menu item Icon
        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[]{
                        color,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor
                }
        );

        navView.setItemTextColor(navMenuTextList);
        navView.setItemIconTintList(navMenuIconList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_search_black_24dp));
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.brown_dark));
        searchEditText.setHintTextColor(getResources().getColor(R.color.brown_dark));
        searchEditText.setHint("Search ...");
        ImageView searchMagIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchMagIcon.setImageResource(R.drawable.ic_close_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 27) {
            ArrayList<Uri> list = (ArrayList<Uri>) data.getSerializableExtra("data");
            showUriList(list);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        setNavMenuItemThemeColors(ContextCompat.getColor(MainActivity.this, R.color.white));
        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_wallet) {

        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_inbox) {
            Intent intent = new Intent(MainActivity.this, ChatRoomActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_search_by) {

        } else if (id == R.id.nav_advance_search) {

        } else if (id == R.id.nav_abouts) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            intent.putExtra("activity", "about");
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            intent.putExtra("activity", "contact");
            startActivity(intent);
        } else if (id == R.id.nav_support) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            intent.putExtra("activity", "contact");
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            // Handle the camera action
            try {

                if (myPreferences.getPreferences(MyPreferences.loginType).equals("" + getResources().getString(R.string.loginType_facebook))) {
                    LoginManager.getInstance().logOut();
                } else if (myPreferences.getPreferences(MyPreferences.loginType).equals("" + getResources().getString(R.string.loginType_google))) {
                    signOut();
                }
                myPreferences.clearPreferences();
                Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showUriList(ArrayList<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        for (Uri uri : uriList) {
            File file = new File(uri.getPath());
            System.out.println("" + file.toString());
        }
        if (uriList.size() > 0) {
            PriceDialog newFragment = PriceDialog.newInstance(uriList.get(0));
            newFragment.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        System.out.println();
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
