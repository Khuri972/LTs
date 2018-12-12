package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.CompanyDetailActivity;
import com.ltsonline.GlobalElements;
import com.ltsonline.ProductDetailActivity;
import com.ltsonline.R;
import com.ltsonline.model.PostModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<PostModel> data = new ArrayList<>();
    Context context;


    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();


    public PostAdapter(Context context, ArrayList<PostModel> da) {
        this.context = context;
        this.data = da;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

        GlobalElements.overrideFonts_P_Nova_Rg_Regular(context, holder.listInquiryMain);
        holder.postTitle.setText("" + data.get(i).getTitle());
        holder.postDesc.setText("" + data.get(i).getDesc());
        holder.postPrice.setText("" + context.getResources().getString(R.string.currency) + data.get(i).getPrice());

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.splash_logo)
                .showImageOnFail(R.drawable.splash_logo)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(data.get(i).getImage_path(), holder.companyImg, options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("activityType", "post");
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*Intent intent = new Intent(context, CompanyDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.company_img)
        ImageView companyImg;
        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_desc)
        TextView postDesc;
        @BindView(R.id.post_price)
        TextView postPrice;
        @BindView(R.id.list_inquiry_main)
        LinearLayout listInquiryMain;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
