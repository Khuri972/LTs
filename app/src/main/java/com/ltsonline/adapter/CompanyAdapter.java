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
import com.ltsonline.R;
import com.ltsonline.custom.FontSource;
import com.ltsonline.model.CompanyModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    ArrayList<CompanyModel> data = new ArrayList<>();
    Context context;

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();


    public CompanyAdapter(Context context, ArrayList<CompanyModel> da) {
        this.context = context;
        this.data = da;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_company, parent, false);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

        GlobalElements.overrideFonts_P_Nova_Rg_Regular(context, holder.listInquiryMain);
        holder.companyName.setTypeface(FontSource.process(context, R.raw.proxima_nova_bold));
        holder.companyName.setText("" + data.get(i).getName());
        holder.companyEmail.setText("" + data.get(i).getEmail());
        holder.companyMobile.setText("" + data.get(i).getMobile());
        holder.companyAddress.setText("" + data.get(i).getAddress());
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
                Intent intent = new Intent(context, CompanyDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);
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
        @BindView(R.id.company_name)
        TextView companyName;
        @BindView(R.id.company_email)
        TextView companyEmail;
        @BindView(R.id.company_mobile)
        TextView companyMobile;
        @BindView(R.id.company_address)
        TextView companyAddress;
        @BindView(R.id.list_inquiry_main)
        LinearLayout listInquiryMain;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
