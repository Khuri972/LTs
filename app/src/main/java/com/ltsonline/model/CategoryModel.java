package com.ltsonline.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by CRAFT BOX on 11/23/2016.
 */

public class CategoryModel implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getBanner_image_path() {
        return banner_image_path;
    }

    public void setBanner_image_path(String banner_image_path) {
        this.banner_image_path = banner_image_path;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    int id,	display_order;
    String name,slug,image_path,banner_image_path,descr;

    public boolean ischeck() {
        return ischeck;
    }

    public void setcheck(boolean ischeck) {
        this.ischeck = ischeck;
    }


    boolean ischeck;
    public CategoryModel()
    {

    }

}
