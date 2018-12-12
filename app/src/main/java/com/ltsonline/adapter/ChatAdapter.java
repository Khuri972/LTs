package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.R;
import com.ltsonline.custom.ScaleImageView;
import com.ltsonline.model.MessageModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    ArrayList<MessageModel> data = new ArrayList<>();
    Context context;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    String uid;
    long img_name;

    public ChatAdapter(Context context, ArrayList<MessageModel> da, String uid) {
        this.context = context;
        this.data = da;
        this.uid = uid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_chat_item, parent, false);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (data.get(position).getFlag().equals("0")) {

            if (data.get(position).getMessage_type().equals("1")) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.RIGHT;
                params.setMargins(5, 5, 5, 5);
                //holder.cc_message.setLayoutParams(params);
                holder.ll_message.setBackgroundResource(R.drawable.bubble_in);
                holder.ll_message.setLayoutParams(params);


                holder.img_message.setVisibility(View.VISIBLE);
                holder.message.setVisibility(View.GONE);

                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.drawable.banner)
                        .showImageForEmptyUri(R.drawable.banner)
                        .showImageOnFail(R.drawable.banner)
                        .build();
                imageLoader.displayImage(data.get(position).getAttachmenr_url(), holder.img_message, options);

                //   holder.img_message.setImageBitmap(data.get(position).getImage());
                holder.message.setText("" + data.get(position).getMessage());
                holder.time.setText("" + data.get(position).getTime());
            } else {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.RIGHT;
                params.setMargins(5, 5, 5, 5);
                //holder.cc_message.setLayoutParams(params);
                holder.ll_message.setBackgroundResource(R.drawable.bubble_in);
                holder.ll_message.setLayoutParams(params);

                holder.message.setVisibility(View.VISIBLE);
                holder.img_message.setVisibility(View.GONE);
                holder.message.setText("" + data.get(position).getMessage());
                holder.time.setText("" + data.get(position).getTime());
            }
        } else if (data.get(position).getFlag().equals("1")) {
            if (data.get(position).getMessage_type().equals("1")) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.LEFT;
                params.setMargins(5, 5, 5, 5);
                //holder.cc_message.setLayoutParams(params);
                holder.ll_message.setBackgroundResource(R.drawable.bubble_out);
                holder.ll_message.setLayoutParams(params);
                holder.img_message.setVisibility(View.VISIBLE);
                holder.message.setVisibility(View.GONE);

                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.drawable.banner)
                        .showImageForEmptyUri(R.drawable.banner)
                        .showImageOnFail(R.drawable.banner)
                        .build();
                imageLoader.displayImage(data.get(position).getAttachmenr_url(), holder.img_message, options);

                holder.img_message.setImageBitmap(data.get(position).getImage());
                holder.message.setText("" + data.get(position).getMessage());
                holder.time.setText("" + data.get(position).getTime());
            } else {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.LEFT;
                params.setMargins(5, 5, 5, 5);
                //holder.cc_message.setLayoutParams(params);
                holder.ll_message.setBackgroundResource(R.drawable.bubble_out);
                holder.ll_message.setLayoutParams(params);

                holder.message.setVisibility(View.VISIBLE);
                holder.img_message.setVisibility(View.GONE);
                holder.message.setText("" + data.get(position).getMessage());
                holder.time.setText("" + data.get(position).getTime());
            }
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView message, time;
        ScaleImageView img_message;
        LinearLayout ll_message;
       // CardView cc_message;

        public ViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.tv_message);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            img_message = (ScaleImageView) itemView.findViewById(R.id.img_message);
            ll_message = (LinearLayout) itemView.findViewById(R.id.ll_message);
            //cc_message = (CardView) itemView.findViewById(R.id.cc_message);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
        try {
            File root = Environment.getExternalStorageDirectory();
            File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/'" + img_name + "'.jpg");
            boolean deleted = cachePath.delete();
            img_name = System.currentTimeMillis();
            Log.d("MyAdapter", "onActivityResult");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
