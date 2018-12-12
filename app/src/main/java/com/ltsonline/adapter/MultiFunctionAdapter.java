package com.ltsonline.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ltsonline.R;
import com.ltsonline.model.HomeMultiModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


/**
 * Created by CRAFT BOX on 11/28/2016.
 */

public class MultiFunctionAdapter extends RecyclerView.Adapter<MultiFunctionAdapter.ViewHolder> {

    Context context;
    ArrayList<HomeMultiModel> data = new ArrayList<>();

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    int ViewPostion = 0;

    public MultiFunctionAdapter(Context context, ArrayList<HomeMultiModel> da) {
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
        View vi = null;
        try {
            Log.e("==>>", "" + viewType);
            if (data.get(ViewPostion).getType() == 1) {
                vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycleview, parent, false);
            } else {
                vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adds, parent, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ViewHolder(vi);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            if (data.get(position).getType() == 1) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                HomeAdapter itemListDataAdapter = new HomeAdapter(context, data.get(position).getHomeModels());
                holder.recyclerView.setLayoutManager(staggeredGridLayoutManager);
                holder.recyclerView.setAdapter(itemListDataAdapter);
            } else {
                holder.addImage.setImageResource(R.drawable.adds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView addImage;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.list_item_recycleview);
            addImage = (ImageView) itemView.findViewById(R.id.adds_img);
        }
    }
}
