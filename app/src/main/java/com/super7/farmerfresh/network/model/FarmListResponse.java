package com.super7.farmerfresh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmListResponse {

    @SerializedName("farm_id")
    @Expose
    private String farmId;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("farm_loc_lat")
    @Expose
    private String farmLocLat;
    @SerializedName("farm_loc_lng")
    @Expose
    private String farmLocLng;
    @SerializedName("farm_address")
    @Expose
    private String farmAddress;
    @SerializedName("farm_description")
    @Expose
    private String farmDescription;
    @SerializedName("farm_img")
    @Expose
    private String farmImg;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmLocLat() {
        return farmLocLat;
    }

    public void setFarmLocLat(String farmLocLat) {
        this.farmLocLat = farmLocLat;
    }

    public String getFarmLocLng() {
        return farmLocLng;
    }

    public void setFarmLocLng(String farmLocLng) {
        this.farmLocLng = farmLocLng;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public String getFarmDescription() {
        return farmDescription;
    }

    public void setFarmDescription(String farmDescription) {
        this.farmDescription = farmDescription;
    }

    public String getFarmImg() {
        return farmImg;
    }

    public void setFarmImg(String farmImg) {
        this.farmImg = farmImg;
    }
    
}