package com.ltsonline.model;

import java.util.ArrayList;

public class HomeMultiModel {

    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<HomeModel> getHomeModels() {
        return homeModels;
    }

    public void setHomeModels(ArrayList<HomeModel> homeModels) {
        this.homeModels = homeModels;
    }

    ArrayList<HomeModel> homeModels=new ArrayList<>();
}
