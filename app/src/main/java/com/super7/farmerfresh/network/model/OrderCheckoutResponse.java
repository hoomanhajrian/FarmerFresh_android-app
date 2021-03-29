package com.super7.farmerfresh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderCheckoutResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("order_date")
        @Expose
        private String orderDate;
        @SerializedName("order_schedule")
        @Expose
        private String orderSchedule;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("order_quantity")
        @Expose
        private String orderQuantity;

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getOrderSchedule() {
            return orderSchedule;
        }

        public void setOrderSchedule(String orderSchedule) {
            this.orderSchedule = orderSchedule;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOrderQuantity() {
            return orderQuantity;
        }

        public void setOrderQuantity(String orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

    }

}