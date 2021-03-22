package com.super7.farmerfresh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmsProductResponse {
    @SerializedName("product_id")
    @Expose
    private Long productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_fname")
    @Expose
    private String productFname;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("organic")
    @Expose
    private Long organic;
    @SerializedName("product_img")
    @Expose
    private String productImg;
    @SerializedName("order_order_id")
    @Expose
    private Long orderOrderId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductFname() {
        return productFname;
    }

    public void setProductFname(String productFname) {
        this.productFname = productFname;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Long getOrganic() {
        return organic;
    }

    public void setOrganic(Long organic) {
        this.organic = organic;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Long getOrderOrderId() {
        return orderOrderId;
    }

    public void setOrderOrderId(Long orderOrderId) {
        this.orderOrderId = orderOrderId;
    }
}
