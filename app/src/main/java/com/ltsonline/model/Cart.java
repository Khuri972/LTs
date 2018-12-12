package com.ltsonline.model;

import java.util.ArrayList;

/**
 * Created by CB-PHP-1 on 8/16/2016.
 */
public class Cart {
    int cart_id;
    double total_ship_charge=0;

    public double getShipping_discount() {
        return shipping_discount;
    }

    public void setShipping_discount(double shipping_discount) {
        this.shipping_discount = shipping_discount;
    }

    double shipping_discount;

    public double getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(double grandtotal) {
        this.grandtotal = grandtotal;
    }

    double grandtotal;
    int discount=0;
    double sub_total=0;
    String orderDate;
    String shipDate;
    String rcdate;
    String orderstatus;

    public String getTrack_url() {
        return track_url;
    }

    public void setTrack_url(String track_url) {
        this.track_url = track_url;
    }

    String track_url;

    public int getOrderstatus_slug() {
        return orderstatus_slug;
    }

    public void setOrderstatus_slug(int orderstatus_slug) {
        this.orderstatus_slug = orderstatus_slug;
    }

    int    orderstatus_slug;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getRcdate() {
        return rcdate;
    }

    public void setRcdate(String rcdate) {
        this.rcdate = rcdate;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    ArrayList<CartItem> cartItems;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public double getTotal_ship_charge() {
        return total_ship_charge;
    }

    public void setTotal_ship_charge(double total_ship_charge) {
        this.total_ship_charge = total_ship_charge;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }
}
