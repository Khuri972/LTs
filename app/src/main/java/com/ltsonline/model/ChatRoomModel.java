package com.ltsonline.model;

import java.io.Serializable;

/**
 * Created by craftbox-2 on 19-Dec-16.
 */

public class ChatRoomModel implements Serializable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SellerModel getSellerModel() {
        return sellerModel;
    }

    public void setSellerModel(SellerModel sellerModel) {
        this.sellerModel = sellerModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public MessageModel getLastMessageModel() {
        return lastMessageModel;
    }

    public void setLastMessageModel(MessageModel lastMessageModel) {
        this.lastMessageModel = lastMessageModel;
    }

    String id;
    SellerModel sellerModel;
    UserModel userModel;
    MessageModel lastMessageModel;
}
