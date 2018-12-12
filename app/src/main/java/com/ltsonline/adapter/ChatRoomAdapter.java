package com.ltsonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ltsonline.ChatActivity;
import com.ltsonline.R;
import com.ltsonline.model.ChatRoomModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by froger_mcs on 11.11.14.
 */
public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {

    private Context context;
    ArrayList<ChatRoomModel> data = new ArrayList<>();
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ChatRoomAdapter(Context context, ArrayList<ChatRoomModel> da) {
        this.context = context;
        this.data = da;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.single_chatroom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            if (data.get(position).getLastMessageModel() != null) {
                holder.tvComment.setText("" + data.get(position).getLastMessageModel().getMessage());
            }

            if (data.get(position).getLastMessageModel().equals("")) {
                holder.tvComment.setVisibility(View.GONE);
            } else {
                holder.tvComment.setVisibility(View.GONE);
            }

            holder.name.setText("" + data.get(position).getSellerModel().getName());

            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.defult_product)
                    .showImageOnFail(R.drawable.defult_product)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            imageLoader.displayImage(data.get(position).getSellerModel().getImage_path(), holder.ivUserAvatar, options);

            holder.fl_chatroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("seller_id", data.get(position).getSellerModel().getId());
                    b.putSerializable("chat_room_info", data.get(position));
                    i.putExtras(b);
                    context.startActivity(i);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivUserAvatar)
        CircleImageView ivUserAvatar;
        @Nullable
        @BindView(R.id.item_last_message)
        TextView tvComment;
        @BindView(R.id.item_seller_name)
        TextView name;

        @BindView(R.id.fl_chatroom)
        FrameLayout fl_chatroom;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
