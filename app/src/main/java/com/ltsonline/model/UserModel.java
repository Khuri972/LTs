package com.ltsonline.model;

import java.io.Serializable;

/**
 * Created by craftbox-2 on 17-Dec-16.
 */

public class UserModel implements Serializable {
    String id;
    String name;
    String image_path;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
