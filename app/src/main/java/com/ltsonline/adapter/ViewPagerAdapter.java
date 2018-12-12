package com.ltsonline.adapter;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ltsonline.R;
import com.ltsonline.custom.ScaleImageView;
import com.ltsonline.model.GeneralViewpager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by CRAFT BOX on 11/18/2016.
 */

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<GeneralViewpager> data;
    android.content.Context Context;
    LayoutInflater mLayoutInflater;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ViewPagerAdapter(android.content.Context Context, ArrayList<GeneralViewpager> da) {
        this.Context = Context;
        data = da;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View ssView, Object ssObject) {
        return ssView == ((LinearLayout) ssObject);
    }

    @Override
    public Object instantiateItem(ViewGroup ssContainer, int ssPosition) {

        mLayoutInflater = (LayoutInflater) Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mLayoutInflater.inflate(R.layout.list_viewpage, ssContainer, false);
        ImageView img = (ImageView) itemView.findViewById(R.id.view_img);

        if (data.get(ssPosition).getUrl().equals("")) {
            //img.setImageResource(R.drawable.header);
        } else {
            try {
                imageLoader.init(ImageLoaderConfiguration.createDefault(Context));
                options = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.drawable.banner)
                        .showImageForEmptyUri(R.drawable.banner)
                        .showImageOnFail(R.drawable.banner)
                        .cacheInMemory(true)
                        .build();
                imageLoader.displayImage(data.get(ssPosition).getUrl(), img, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ssContainer.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup ssContainer, int ssPosition,
                            Object ssObject) {
        ((ViewPager) ssContainer).removeView((LinearLayout) ssObject);
    }
}
