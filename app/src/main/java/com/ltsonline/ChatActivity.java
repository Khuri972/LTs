package com.ltsonline;

import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ltsonline.adapter.ChatAdapter;
import com.ltsonline.model.MessageModel;
import com.ltsonline.model.SellerModel;
import com.ltsonline.model.UserModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    ArrayList<MessageModel> data = new ArrayList<>();
    RecyclerView rcl_chat;
    EditText edt_message;
    ChatAdapter adapter;
    ImageView img_attach;
    String seller_id = "";
    String uid = "";
    UserModel user_info; // My Info
    String content = "";
    BroadcastReceiver receiver;
    Bitmap selected_image;
    int flagd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_chat);
        GlobalElements.setActionBar(ChatActivity.this, "Chat");

        rcl_chat = (RecyclerView) findViewById(R.id.recv_chat);
        edt_message = (EditText) findViewById(R.id.edt_message);
        img_attach = (ImageView) findViewById(R.id.img_attach);

        adapter = new ChatAdapter(ChatActivity.this, data, "");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(false);
        rcl_chat.setLayoutManager(mLayoutManager);
        rcl_chat.setItemAnimator(new DefaultItemAnimator());
        rcl_chat.setAdapter(adapter);

        edt_message.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edt_message.getRight() - edt_message.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        DateFormat df = new SimpleDateFormat("M d hh:mm");
                        Date dateobj = new Date();
                        if (!edt_message.getText().toString().equals("")) {
                            content = edt_message.getText().toString();
                            if (flagd == 0) {
                                flagd = 1;
                                MessageModel m = new MessageModel("1", "" + content, null, "hardip", "jay", "0", "12-05-2018", "0", "");
                                data.add(m);
                            } else {
                                flagd = 0;
                                MessageModel m = new MessageModel("1", "" + content, null, "hardip", "jay", "1", "12-05-2018", "0", "");
                                data.add(m);
                            }

                            adapter.notifyDataSetChanged();
                            rcl_chat.smoothScrollToPosition(View.FOCUS_UP);
                            edt_message.setText("");
                            content = "";
                            // new GetChatRoom().execute("addMessage");
                            return true;
                        }

                    }
                }
                return false;
            }
        });
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
