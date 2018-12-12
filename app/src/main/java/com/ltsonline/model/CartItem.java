package com.ltsonline.model;

/**
 * Created by CB-PHP-1 on 8/16/2016.
 */
public class CartItem {


    int cart_id;
    int id;
    String qty;
    String orderdate;
    String shipdate;
    String deliverydate;
    String orderstatus;
    String total_ship_charge;
    String payment_method;
    String finaltotal;
    String address1;
    String zip;
    String phone;
    String email;
    String city;
    String state;
    String country;

    public ProductModel getP() {
        return p;
    }

    public void setP(ProductModel p) {
        this.p = p;
    }

    ProductModel p;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getShipdate() {
        return shipdate;
    }

    public void setShipdate(String shipdate) {
        this.shipdate = shipdate;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getTotal_ship_charge() {
        return total_ship_charge;
    }

    public void setTotal_ship_charge(String total_ship_charge) {
        this.total_ship_charge = total_ship_charge;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getFinaltotal() {
        return finaltotal;
    }

    public void setFinaltotal(String finaltotal) {
        this.finaltotal = finaltotal;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
