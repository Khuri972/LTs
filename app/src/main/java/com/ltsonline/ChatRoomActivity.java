package com.ltsonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.ltsonline.adapter.ChatRoomAdapter;
import com.ltsonline.model.ChatRoomModel;
import com.ltsonline.model.MessageModel;
import com.ltsonline.model.SellerModel;
import com.ltsonline.model.UserModel;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRoomActivity extends AppCompatActivity {

    @BindView(R.id.rv_chatroom)
    RecyclerView recyclerView;
    @BindView(R.id.activity_chat_room)
    RelativeLayout activityChatRoom;

    ChatRoomAdapter chatRoomAdapter;
    ArrayList<ChatRoomModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_chat_room);
        GlobalElements.setActionBar(ChatRoomActivity.this, "Messages");
        ButterKnife.bind(this);

        ChatRoomModel chatRoomModel = new ChatRoomModel();
        SellerModel seller_info = new SellerModel();
        seller_info = new SellerModel();
        seller_info.setId("1");
        seller_info.setName("Yagnik");
        seller_info.setEmail("");
        seller_info.setImage_path("");
        chatRoomModel.setSellerModel(seller_info);
        UserModel user_info = new UserModel();
        user_info = new UserModel();
        user_info.setName("Hardip");
        user_info.setEmail("");
        user_info.setImage_path("");
        chatRoomModel.setUserModel(user_info);
        for (int j = 0; j < 5; j++) {
            //JSONObject message_json = messages_json.getJSONObject(j);
            MessageModel m = new MessageModel();
            chatRoomModel.setLastMessageModel(m);
        }
        data.add(chatRoomModel);
        data.add(chatRoomModel);
        data.add(chatRoomModel);
        data.add(chatRoomModel);

        chatRoomAdapter = new ChatRoomAdapter(ChatRoomActivity.this, data);
        recyclerView.setAdapter(chatRoomAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatRoomActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
