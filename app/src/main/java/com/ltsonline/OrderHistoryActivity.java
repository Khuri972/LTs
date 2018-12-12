package com.ltsonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ltsonline.adapter.ShoppingCartAdapter;
import com.ltsonline.model.Cart;
import com.roughike.bottombar.BottomBar;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryActivity extends AppCompatActivity {
    ArrayList<Cart> cart = new ArrayList<>();
    @BindView(R.id.order_history_recycleview)
    RecyclerView recyclerView;
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
        setContentView(R.layout.activity_order_history);
        ButterKnife.bind(this);
        GlobalElements.setActionBar(OrderHistoryActivity.this, "Order History");

        for (int i = 0; i < 10; i++) {
            // JSONObject obj = result.getJSONObject(i);
            Cart c = new Cart();
            c.setCart_id(i);
            c.setOrderDate("" + GlobalElements.getDate());
            c.setShipDate(GlobalElements.getDate());
            c.setGrandtotal(2510);
            c.setTotal_ship_charge(10);
            c.setOrderstatus("Shiped");
            c.setOrderstatus_slug(4);
            c.setRcdate("");
            c.setDiscount(10);
            c.setTrack_url("ltsonline.com");
            cart.add(c);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OrderHistoryActivity.this, 1);
        recyclerView.setAdapter(new ShoppingCartAdapter(OrderHistoryActivity.this, cart));
        recyclerView.setLayoutManager(layoutManager);

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
