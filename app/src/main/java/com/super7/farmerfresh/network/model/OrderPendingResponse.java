package com.super7.farmerfresh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPendingResponse {
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("order_schedule")
    @Expose
    private String orderSchedule;
    @SerializedName("user_user_id")
    @Expose
    private Long userUserId;
    @SerializedName("pickup_or_delivery")
    @Expose
    private String pickupOrDelivery;
    @SerializedName("order_quantity")
    @Expose
    private String orderQuantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("order_loc_lat")
    @Expose
    private Object orderLocLat;
    @SerializedName("order_loc_lng")
    @Expose
    private Object orderLocLng;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSchedule() {
        return orderSchedule;
    }

    public void setOrderSchedule(String orderSchedule) {
        this.orderSchedule = orderSchedule;
    }

    public Long getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(Long userUserId) {
        this.userUserId = userUserId;
    }

    public String getPickupOrDelivery() {
        return pickupOrDelivery;
    }

    public void setPickupOrDelivery(String pickupOrDelivery) {
        this.pickupOrDelivery = pickupOrDelivery;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getOrderLocLat() {
        return orderLocLat;
    }

    public void setOrderLocLat(Object orderLocLat) {
        this.orderLocLat = orderLocLat;
    }

    public Object getOrderLocLng() {
        return orderLocLng;
    }

    public void setOrderLocLng(Object orderLocLng) {
        this.orderLocLng = orderLocLng;
    }

}
