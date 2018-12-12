package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ltsonline.ProductDetailActivity;
import com.ltsonline.R;
import com.ltsonline.custom.FontSource;
import com.ltsonline.model.HomeModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by CRAFT BOX on 11/28/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<HomeModel> data = new ArrayList<>();

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    int ViewPostion = 0;

    public HomeAdapter(Context context, ArrayList<HomeModel> da) {
        this.context = context;
        this.data = da;
    }

    @Override
    public int getItemViewType(int position) {
        this.ViewPostion = position;
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home, null);
        return new ViewHolder(vi);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

            holder.listHomePrice.setTypeface(FontSource.process(context, R.raw.proxima_nova_bold));
            holder.listHomeName.setTypeface(FontSource.process(context, R.raw.proxima_nova_rg_regular));

            holder.listHomePrice.setText("₹ " + data.get(position).getPrice());
            holder.listHomeName.setText("" + data.get(position).getName());
            holder.ratingBar.setRating(Float.parseFloat("" + data.get(position).getRating()));

            if (!data.get(position).getImage_path().equals("")) {
                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(R.drawable.category_default)
                        .showImageOnFail(R.drawable.category_default)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();
                imageLoader.displayImage(data.get(position).getImage_path(), holder.listHomeImg, options);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_home_img)
        ImageView listHomeImg;
        @BindView(R.id.list_home_price)
        TextView listHomePrice;
        @BindView(R.id.list_home_name)
        TextView listHomeName;
        @BindView(R.id.review_rat)
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
