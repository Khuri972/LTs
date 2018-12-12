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

import com.ltsonline.CompanyActivity;
import com.ltsonline.ProductDetailActivity;
import com.ltsonline.R;
import com.ltsonline.custom.FontSource;
import com.ltsonline.model.CategoryModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by CRAFT BOX on 11/28/2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModel> data = new ArrayList<>();

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    int ViewPostion = 0;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> da) {
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
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category, null);
        return new ViewHolder(vi);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

            holder.listHomeName.setTypeface(FontSource.process(context, R.raw.proxima_nova_rg_regular));
            holder.listHomeName.setText("" + data.get(position).getName());

            if (!data.get(position).getImage_path().equals("")) {
                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(R.drawable.category_default)
                        .showImageOnFail(R.drawable.category_default)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();
                imageLoader.displayImage(data.get(position).getImage_path(), holder.listHomeImg, options);
            } else {
                if (data.get(position).getName().equals("Bed Set")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_1);
                } else if (data.get(position).getName().equals("Wardrobes & Cabinets")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_2);
                } else if (data.get(position).getName().equals("Mattresses")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_3);
                } else if (data.get(position).getName().equals("Dining set")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_4);
                } else if (data.get(position).getName().equals("Home & Office table")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_5);
                } else if (data.get(position).getName().equals("Metal Almirahs")) {
                    holder.listHomeImg.setImageResource(R.drawable.sub_6);
                }
            }

            holder.listHomeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CompanyActivity.class);
                    intent.putExtra("id", data.get(position).getId());
                    intent.putExtra("category_name", data.get(position).getName());
                    context.startActivity(intent);
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
        @BindView(R.id.list_home_category_img)
        ImageView listHomeImg;
        @BindView(R.id.list_home_category_name)
        TextView listHomeName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
