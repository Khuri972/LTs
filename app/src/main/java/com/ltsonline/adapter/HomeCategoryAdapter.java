package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltsonline.CategoryActivity;
import com.ltsonline.R;
import com.ltsonline.model.CategoryModel;
import com.ltsonline.custom.FontSource;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


/**
 * Created by CRAFT BOX on 11/28/2016.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModel> data = new ArrayList<>();
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public HomeCategoryAdapter(Context context, ArrayList<CategoryModel> da) {
        this.context = context;
        this.data = da;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_category, null);
        return new ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

            holder.category_name.setTypeface(FontSource.process(context, R.raw.proxima_nova_bold));
            holder.category_name.setText("" + data.get(position).getName());

            if (!data.get(position).getImage_path().equals("")) {
                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(R.drawable.category_default)
                        .showImageOnFail(R.drawable.category_default)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();
                imageLoader.displayImage(data.get(position).getImage_path(), holder.image_name, options);
            } else {
                if (data.get(position).getName().equals("Electronic")) {
                    holder.image_name.setImageResource(R.drawable.tv);
                } else if (data.get(position).getName().equals("Furniture")) {
                    holder.image_name.setImageResource(R.drawable.farniture);
                } else if (data.get(position).getName().equals("Properties")) {
                    holder.image_name.setImageResource(R.drawable.realestet);
                } else if (data.get(position).getName().equals("Jobs")) {
                    holder.image_name.setImageResource(R.drawable.jobs);
                } else if (data.get(position).getName().equals("Cars")) {
                    holder.image_name.setImageResource(R.drawable.car);
                }
            }

            holder.image_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(context, CategoryActivity.class);
                        i.putExtra("id", data.get(position).getId());
                        i.putExtra("category_name", data.get(position).getName());
                        i.putExtra("banner_image_path", data.get(position).getBanner_image_path());
                        context.startActivity(i);
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

        ImageView image_name;
        TextView category_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_name = (ImageView) itemView.findViewById(R.id.list_home_category_img);
            category_name = (TextView) itemView.findViewById(R.id.list_home_category_name);
        }
    }
}
