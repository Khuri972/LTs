package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ltsonline.GlobalElements;
import com.ltsonline.OrderDetailActivity;
import com.ltsonline.R;
import com.ltsonline.model.Cart;

import java.util.ArrayList;

/**
 * Created by CB-PHP-1 on 8/12/2016.
 * 'com.android.support:recyclerview-v7:23.2.0'
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private ArrayList<Cart> android;
    private Context context;

    public ShoppingCartAdapter(Context context, ArrayList<Cart> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_single_cart, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Cart p = android.get(i);
        viewHolder.tv_android.setText("#" + p.getCart_id());
        viewHolder.list_home_price.setText("Rs." + p.getGrandtotal());
        viewHolder.list_order_date.setText(p.getOrderDate());
        viewHolder.list_order_status.setText(p.getOrderstatus());

        if (p.getOrderstatus_slug() == 0 || p.getOrderstatus_slug() == 4)  // 0 = cancled , 4 = deliverd
        {
            viewHolder.delete.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.delete.setVisibility(View.VISIBLE);
        }

        if (p.getOrderstatus_slug() == 3) {
            viewHolder.order_track.setVisibility(View.VISIBLE);
        } else {
            viewHolder.order_track.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android, list_home_price, list_order_date, list_order_status, order_track;
        LinearLayout home_linear;
        ImageView delete;

        public ViewHolder(View view) {
            super(view);
            tv_android = (TextView) view.findViewById(R.id.tv_product_name);
            list_home_price = (TextView) view.findViewById(R.id.list_home_price);
            list_order_date = (TextView) view.findViewById(R.id.list_order_date);
            list_order_status = (TextView) view.findViewById(R.id.list_order_status);
            delete = (ImageView) view.findViewById(R.id.list_order_delete);
            home_linear = (LinearLayout) view.findViewById(R.id.home_linear);
            order_track = (TextView) view.findViewById(R.id.list_order_track);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (GlobalElements.isConnectingToInternet(context)) {
                        android.support.v4.app.FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                        CancelorderDialog rd = CancelorderDialog.newInstance(context, uid, "" + android.get(getAdapterPosition()).getCart_id());
                        rd.show(fm, "");
                    } else {
                        GlobalElements.showDialog(context);
                    }*/
                }
            });

            home_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, OrderDetailActivity.class);
                    i.putExtra("cid", "" + android.get(getAdapterPosition()).getCart_id());
                    i.putExtra("order_status_slug", "" + android.get(getAdapterPosition()).getOrderstatus_slug());
                    context.startActivity(i);
                }
            });

            order_track.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent i = new Intent(context, OurPoliciesActivity.class);
                    i.putExtra("title", "Track Your Order");
                    i.putExtra("url", "" + android.get(getAdapterPosition()).getTrack_url()); //todo track url
                    context.startActivity(i);*/
                }
            });
        }
    }

}