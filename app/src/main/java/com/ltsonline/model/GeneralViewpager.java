package com.ltsonline.model;

import java.io.Serializable;

/**
 * Created by CRAFT BOX on 11/18/2016.
 */

public class GeneralViewpager implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    int id;
    String url;
    String name;

    public GeneralViewpager() {

    }

    public GeneralViewpager(int id, String url) {
        this.id = id;
        this.url = url;
    }
}
