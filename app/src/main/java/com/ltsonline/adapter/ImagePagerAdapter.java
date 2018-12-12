package com.ltsonline.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ltsonline.R;
import com.ltsonline.custom.TouchImageView;
import com.ltsonline.model.GeneralViewpager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by CRAFT BOX on 11/18/2016.
 */

public class ImagePagerAdapter extends PagerAdapter {
    ArrayList<GeneralViewpager> data;
    android.content.Context Context;
    LayoutInflater mLayoutInflater;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ImagePagerAdapter(android.content.Context Context, ArrayList<GeneralViewpager> da) {
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
        View itemView = mLayoutInflater.inflate(R.layout.list_viewpage_image, ssContainer, false);
        //ImageView img = (ImageView) itemView.findViewById(R.id.view_img);
        TouchImageView img=(TouchImageView) itemView.findViewById(R.id.wallpaper);
        if (data.get(ssPosition).getUrl().equals("")) {
            //img.setImageResource(R.drawable.header);
        } else {
            try {
                imageLoader.init(ImageLoaderConfiguration.createDefault(Context));
                options = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.drawable.splash_logo)
                        .showImageForEmptyUri(R.drawable.splash_logo)
                        .showImageOnFail(R.drawable.splash_logo)
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
