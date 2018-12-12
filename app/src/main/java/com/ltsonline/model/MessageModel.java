package com.ltsonline.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Craftbox-4 on 12/15/2016.
 */

public class MessageModel implements Serializable {
    String message_id;
    String message = "";
    String attachmenr_url;
    String sender_id;
    String receiver_id;
    String flag;
    String time;

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    String message_type;
    Bitmap image;

    public MessageModel()
    {

    }

    public MessageModel(String message_id, String message, Bitmap image, String sender_id,
                        String receiver_id, String flag, String time, String message_type, String attachmenr_url) {
        this.message_id = message_id;
        this.message = message;
        this.attachmenr_url = attachmenr_url;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.flag = flag;
        this.image = image;
        this.time = time;
        this.message_type = message_type;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachmenr_url() {
        return attachmenr_url;
    }

    public void setAttachmenr_url(String attachmenr_url) {
        this.attachmenr_url = attachmenr_url;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
