package com.ltsonline.model;

import java.util.ArrayList;

/**
 * Created by pratap.kesaboyina on 01-12-2015.
 */
public class ProductModel {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public int getSell_price() {
        return sell_price;
    }

    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public int getIs_wishlist() {
        return is_wishlist;
    }

    public void setIs_wishlist(int is_wishlist) {
        this.is_wishlist = is_wishlist;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    String id;
    String name;
    String category_name;
    String url;
    String description;
    int max_price;
    int discount_price;
    int sell_price;
    int is_wishlist;
    int qty;


    public ProductModel() {
    }

    public ProductModel(String name, String url) {
        this.name = name;
        this.url = url;
    }


}
