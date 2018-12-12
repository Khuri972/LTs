package com.ltsonline;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.custom.ScaleImageView;
import com.ltsonline.model.Cart;
import com.ltsonline.model.CartItem;
import com.ltsonline.model.ProductModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    Cart c;
    ArrayList<CartItem> cartItems;
    RecyclerView recyclerView;
    String uid, cid = "", track_url, order_status_slug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_order_detail);
        GlobalElements.setActionBar(OrderDetailActivity.this, "Order Detail");
        c = new Cart();
        cartItems = new ArrayList<>();
        try {
            Bundle b = getIntent().getExtras();
            cid = b.getString("cid");
            order_status_slug = b.getString("order_status_slug");

        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView = (RecyclerView) findViewById(R.id.order_history_recycleview);

        cartItems.clear();
        c.setCartItems(cartItems);
        c.setCart_id(1);
        c.setTotal_ship_charge(12);
        c.setSub_total(100);
        c.setDiscount(10);
        c.setOrderDate(GlobalElements.getDate());
        c.setOrderstatus("Shiped");
        c.setShipping_discount(5);
        c.setGrandtotal(500);

        for (int i = 0; i < 10; i++) {
            CartItem c = new CartItem();
            ProductModel p = new ProductModel();
            p.setId("1");
            p.setName("Test");
            p.setSell_price(500);
            p.setMax_price(600);
            p.setDiscount_price(200);
            p.setUrl("https://rukminim1.flixcart.com/image/832/832/jnj7iq80/kids-dress/s/2/e/9-10-years-sbngc0422-ftc-fashions-original-imaf4hb2ywhhzmv2.jpeg");
            p.setQty(5);
            c.setP(p);
            cartItems.add(c);
        }
        c.setCartItems(cartItems);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OrderDetailActivity.this, 1);
        recyclerView.setAdapter(new ShoppingCartItemDetailAdapter(OrderDetailActivity.this, c.getCartItems()));
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

    public class ShoppingCartItemDetailAdapter extends RecyclerView.Adapter<ShoppingCartItemDetailAdapter.ViewHolder> {
        private ArrayList<CartItem> android;
        private Context context;
        private DisplayImageOptions options;
        protected ImageLoader imageLoader = ImageLoader.getInstance();

        public ShoppingCartItemDetailAdapter(Context context, ArrayList<CartItem> android) {
            this.android = android;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_single_cart_item_detail, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {

            if (i == 0) {
                viewHolder.linear.setVisibility(View.VISIBLE);
                viewHolder.order.setText("#ORDER" + c.getCart_id());
                viewHolder.order_date.setText("" + c.getOrderDate());
                viewHolder.order_status.setText("" + c.getOrderstatus());
                viewHolder.subtotal.setText("Rs." + c.getSub_total());
                viewHolder.shipping_charge.setText("+ Rs." + c.getTotal_ship_charge());
                viewHolder.shipping_discount.setText("- Rs." + c.getShipping_discount());
                viewHolder.coupon_discount.setText("Rs." + c.getDiscount());
                viewHolder.grandtotal.setText("- Rs." + (c.getGrandtotal()));
            } else {
                viewHolder.linear.setVisibility(View.GONE);
            }

            ProductModel p = android.get(i).getP();
            viewHolder.tv_android.setText(p.getName());
            viewHolder.list_home_weight.setText("" + p.getQty()); //qty
            viewHolder.list_home_price.setText("Rs." + p.getSell_price());

            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.defult_product)
                    .showImageOnFail(R.drawable.defult_product)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            imageLoader.displayImage(p.getUrl(), viewHolder.img_android, options);
        }

        @Override
        public int getItemCount() {
            return android.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_android, list_home_weight, list_home_discount_price, list_home_price, order, order_date, order_status, subtotal, shipping_charge, shipping_discount, coupon_discount, grandtotal;
            private ScaleImageView img_android;
            LinearLayout linear;

            public ViewHolder(View view) {
                super(view);
                tv_android = (TextView) view.findViewById(R.id.tv_product_name);
                order = (TextView) view.findViewById(R.id.cart_detail_order);
                order_date = (TextView) view.findViewById(R.id.cart_detail_date);
                order_status = (TextView) view.findViewById(R.id.cart_detail_status);
                subtotal = (TextView) view.findViewById(R.id.cart_detail_subtotal);
                shipping_charge = (TextView) view.findViewById(R.id.cart_detail_shipping);
                shipping_discount = (TextView) view.findViewById(R.id.cart_detail_discount);
                grandtotal = (TextView) view.findViewById(R.id.cart_detail_grand);
                coupon_discount = (TextView) view.findViewById(R.id.cart_detail_discount_coupon);
                list_home_weight = (TextView) view.findViewById(R.id.list_home_weight); // qty
                list_home_discount_price = (TextView) view.findViewById(R.id.list_home_discount_price);
                list_home_price = (TextView) view.findViewById(R.id.list_home_price);
                img_android = (ScaleImageView) view.findViewById(R.id.list_home_img);
                linear = (LinearLayout) view.findViewById(R.id.shopping_linear);
            }
        }
    }
}
