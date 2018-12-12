package com.ltsonline;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ltsonline.custom.FontSource;
import com.ltsonline.custom.WrapContentViewPager;
import com.ltsonline.model.CompanyModel;
import com.ltsonline.model.GeneralViewpager;
import com.matrixxun.starry.badgetextview.MenuItemBadge;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.category_detail)
    TextView categoryDetail;
    @BindView(R.id.call_txt)
    TextView callTxt;
    @BindView(R.id.chat_txt)
    TextView chatTxt;
    @BindView(R.id.view_img)
    ImageView view_img;

    @BindView(R.id.product_detail_nested)
    NestedScrollView productDetailNested;
    @BindView(R.id.main_view_pager)
    WrapContentViewPager mainViewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.signup_sumbit)
    TextView signupSumbit;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.view_profile_layout)
    LinearLayout viewProfileLayout;
    @BindView(R.id.facebook)
    ImageView facebook;
    @BindView(R.id.whatsapp)
    ImageView whatsapp;

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    String url = "http://www.esa.int/var/esa/storage/images/esa_multimedia/images/2016/04/sentinel-1b_s_first_image/15966791-2-eng-GB/Sentinel-1B_s_first_image_fullwidth.jpg";
    //String url = "https://www.w3schools.com/w3images/fjords.jpg";
    String activityType = "";
    ArrayList<GeneralViewpager> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        GlobalElements.setActionBar(ProductDetailActivity.this, "Product Detail");

        try {
            Intent intent = getIntent();
            activityType = intent.getStringExtra("activityType");
            if (activityType == null) {
                activityType = "";
            }

            if (activityType.equals("post")) {
                chatTxt.setText("CHAT ME WITH ME");
            } else {
                chatTxt.setText("ADD TO CART");
            }
        } catch (Exception e) {
            e.printStackTrace();
            activityType = "";
        }

        callTxt.setTypeface(FontSource.process(ProductDetailActivity.this, R.raw.proxima_nova_rg_regular));
        chatTxt.setTypeface(FontSource.process(ProductDetailActivity.this, R.raw.proxima_nova_rg_regular));

        callTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGranted()) {
                    call_action();
                }
            }
        });

        chatTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (activityType.equals("post")) {
                        Intent i = new Intent(ProductDetailActivity.this, ChatActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("seller_id", "");
                        i.putExtras(b);
                        startActivity(i);
                    } else {
                        Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CompanyModel da = new CompanyModel();
                    da.setId("1");
                    da.setName("Lts Online");
                    da.setMobile("9898989898");
                    da.setEmail("lts@gmail.com");
                    da.setAddress("Rajkot 360005");
                    da.setImage_path("");
                    Intent intent = new Intent(ProductDetailActivity.this, CompanyDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", da);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {

            view_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProductDetailActivity.this, ViewAltImageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            data.clear();
            GeneralViewpager da = new GeneralViewpager();
            da.setUrl("" + url);
            data.add(da);
            da = new GeneralViewpager();
            da.setUrl("https://www.setaswall.com/wp-content/uploads/2017/10/Warrior-Image-Wallpaper-1080x1920.jpg");
            data.add(da);
            data.add(da);
            data.add(da);

            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.banner)
                    .showImageForEmptyUri(R.drawable.banner)
                    .showImageOnFail(R.drawable.banner)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            imageLoader.displayImage(url, view_img, options);

            imageLoader.loadImage(url, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int toolbarHeight = view_img.getHeight();
                                try {
                                    DisplayMetrics displayMetrics = new DisplayMetrics();
                                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                    int height = displayMetrics.heightPixels;
                                    int displayheight = height / 2;
                                    if (displayheight < toolbarHeight) {
                                        toolbarHeight = displayheight - 400;
                                    } else {
                                        toolbarHeight = height - toolbarHeight;
                                    }
                                    // toolbarHeight = height - toolbarHeight;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                View bottomSheet = findViewById(R.id.product_detail_nested);
                                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                                bottomSheetBehavior.setPeekHeight(toolbarHeight);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 200);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    int toolbarHeight = view_img.getHeight();
                    try {
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels;
                        int displayheight = height / 2;
                        if (displayheight < toolbarHeight) {
                            toolbarHeight = displayheight - 400;
                        } else {
                            toolbarHeight = height - toolbarHeight;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    View bottomSheet = findViewById(R.id.product_detail_nested);
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                    bottomSheetBehavior.setPeekHeight(toolbarHeight);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 200);

        categoryDetail.setText("" + GlobalElements.fromHtml("<b>Make Maruti Suzuki<b><br>Model Balneo<br>Variant : VSI<br>Vehical TYpe : Hatcback<br><br><b>Color : Red<br>Milege : 21 KM/L<br>Registration no :Gj3 6202<br>Condition : Top"));

    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void call_action() {
        String phnum = "9979045113";
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phnum));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.shopping_cart:
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail, menu);

        MenuItem menuItemNotification = menu.findItem(R.id.shopping_cart);
        MenuItemBadge.update(this, menuItemNotification, new MenuItemBadge.Builder()
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.shopping_bag))
                .iconTintColor(Color.BLACK)
                .textBackgroundColor(Color.parseColor("#EF4738"))
                .textColor(Color.BLACK));
        MenuItemBadge.getBadgeTextView(menuItemNotification).setBadgeCount("5");

        return super.onCreateOptionsMenu(menu);
    }

}
