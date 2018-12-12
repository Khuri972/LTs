package com.ltsonline;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.adapter.CartAdapter;
import com.ltsonline.model.ProductModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView cart_total_item, cart_total_item_count, bag_total, big_discount, sub_total, coupon_discount, delivery, delivery_charge, total_paybale,
            delivery_option, submit, cart_empty;

    LinearLayout coupon_code, gift_wrap;
    RecyclerView recyclerView;
    ArrayList<ProductModel> data = new ArrayList<>();
    String uid, cart_id, pincode = "", isDeliveryPincodeAvailable; // todo isDeliveryPincodeAvailable = 1 or 0

    ImageView edit_pincode, cart_remove_coupon;
    NestedScrollView nested;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_cart);
        GlobalElements.setActionBar(CartActivity.this, "My Cart");

        nested = (NestedScrollView) findViewById(R.id.cart_nested);
        cart_empty = (TextView) findViewById(R.id.cart_empty);
        cart_total_item = (TextView) findViewById(R.id.cart_total_item);
        cart_total_item_count = (TextView) findViewById(R.id.cart_total_item_count);
        bag_total = (TextView) findViewById(R.id.cart_bag_total);
        big_discount = (TextView) findViewById(R.id.cart_big_discount);
        sub_total = (TextView) findViewById(R.id.cart_sub_total);
        coupon_discount = (TextView) findViewById(R.id.cart_copun_discount);

        delivery = (TextView) findViewById(R.id.cart_delivery);
        delivery_charge = (TextView) findViewById(R.id.cart_delivery_charge);
        delivery_option = (TextView) findViewById(R.id.cart_deliver_option);
        edit_pincode = (ImageView) findViewById(R.id.cart_edit_pin);
        cart_remove_coupon = (ImageView) findViewById(R.id.cart_remove_coupon);
        coupon_code = (LinearLayout) findViewById(R.id.cart_apply_coupon);
        recyclerView = (RecyclerView) findViewById(R.id.cart_recycleview);
        total_paybale = (TextView) findViewById(R.id.cart_total_payabale);
        submit = (TextView) findViewById(R.id.cart_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, BillingDetailActivity.class);
                i.putExtra("uid", "" + uid);
                i.putExtra("pincode", "" + pincode);
                i.putExtra("cart_id", "" + cart_id);
                startActivity(i);
            }
        });


        bag_total.setText("Rs.0");
        big_discount.setText("Rs.0");
        sub_total.setText("Rs.0");
        coupon_discount.setText("Rs.0");
        delivery.setText("--");
        total_paybale.setText("Rs.0");

        ProductModel da = new ProductModel();
        da.setId("1");
        da.setName("abc");
        da.setUrl("https://rukminim1.flixcart.com/image/832/832/jnj7iq80/kids-dress/s/2/e/9-10-years-sbngc0422-ftc-fashions-original-imaf4hb2ywhhzmv2.jpeg");
        da.setMax_price(1200);
        da.setSell_price(1500);
        da.setDiscount_price(10);
        da.setIs_wishlist(0);
        data.add(da);

        da = new ProductModel();
        da.setId("1");
        da.setName("abc");
        da.setUrl("https://rukminim1.flixcart.com/image/832/832/jnj7iq80/kids-dress/s/2/e/9-10-years-sbngc0422-ftc-fashions-original-imaf4hb2ywhhzmv2.jpeg");
        da.setMax_price(1200);
        da.setSell_price(1500);
        da.setDiscount_price(10);
        da.setIs_wishlist(0);
        data.add(da);


        CartAdapter itemListDataAdapter = new CartAdapter(CartActivity.this, data, uid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(itemListDataAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
