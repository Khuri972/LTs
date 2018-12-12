package com.ltsonline.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.R;
import com.ltsonline.custom.FontSource;
import com.ltsonline.model.CategoryModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by CRAFT BOX on 11/28/2016.
 */

public class ChooseCategoryAdapter extends RecyclerView.Adapter<ChooseCategoryAdapter.ViewHolder> {

    Context context;
    FragmentManager fm;
    ArrayList<CategoryModel> data = new ArrayList<>();
    String price;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ChooseCategoryAdapter(Context context, ArrayList<CategoryModel> da, FragmentManager fm, String price) {
        this.context = context;
        this.data = da;
        this.fm = fm;
        this.price = price;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_choose_category, null);
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
            }
            if (data.get(position).ischeck()) {
                holder.layuout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            } else {
                holder.layuout.setBackgroundColor(context.getResources().getColor(R.color.gray));
            }

                    holder.layuout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        for (int i = 0; i < data.size(); i++) {
                            if (i == position) {
                                data.get(position).setcheck(true);
                            } else {
                                data.get(i).setcheck(false);
                            }
                        }
                        notifyDataSetChanged();
                        /*PostAdDialog newFragment = PostAdDialog.newInstance(price, data.get(position).getId() + "", "0");
                        newFragment.show(fm, "dialog");*/

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
        LinearLayout layuout;

        public ViewHolder(View itemView) {
            super(itemView);
            image_name = (ImageView) itemView.findViewById(R.id.list_home_category_img);
            category_name = (TextView) itemView.findViewById(R.id.list_home_category_name);
            layuout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
}
