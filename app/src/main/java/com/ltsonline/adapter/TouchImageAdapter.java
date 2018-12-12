package com.ltsonline.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

public class TouchImageAdapter extends PagerAdapter {

    private ArrayList<GeneralViewpager> notes = null;

    public String imgpath;
    private final String string = null;
    DisplayImageOptions options;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context activity;

    public TouchImageAdapter(Context act, ArrayList<GeneralViewpager> allNotes) {
        activity = act;
        notes = allNotes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        TouchImageView img = new TouchImageView(container.getContext());
        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        img.setAdjustViewBounds(true);
        String imagename = notes.get(position).getUrl();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imgpath = "" + imagename;
        options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.drawable.defult_product)
                .showImageForEmptyUri(R.drawable.defult_product)
                .showImageOnFail(R.drawable.defult_product)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(imgpath, img, options);

        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
