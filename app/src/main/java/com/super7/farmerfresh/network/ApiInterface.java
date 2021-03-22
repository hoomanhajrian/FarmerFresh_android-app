package com.super7.farmerfresh.network;

import com.super7.farmerfresh.network.model.AddCartResponse;
import com.super7.farmerfresh.network.model.CartListResponse;
import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.network.model.FarmsProductResponse;
import com.super7.farmerfresh.network.model.ProductListResponse;
import com.super7.farmerfresh.network.model.RemoveCartItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // Get farms list
    @GET("V1/farms")
    Call<List<FarmListResponse>> getFarms();

    // Get Farms Products
    @FormUrlEncoded
    @POST("V1/getfarmproducts")
    Call<List<FarmsProductResponse>> getFarmsProduct(@Field("farm_id") String farm_id);

    // Get product list
    @GET("V1/products")
    Call<List<ProductListResponse>> getProduct();

    // Add to Cart
    @FormUrlEncoded
    @POST("V1/addtocart")
    Call<AddCartResponse> addtocart(@Field("farm_name") String farm_name, @Field("product_name") String product_name,
                                    @Field("product_image") String product_image, @Field("product_family_name") String product_family_name,
                                    @Field("product_price") String product_price, @Field("product_description") String product_description,
                                    @Field("quantity") String quantity);

    // Cart items for particular user
    @GET("V1/showcart")
    Call<List<CartListResponse>> getCarts(@Query("userid") String userid);

    // Delete the cart item
    @FormUrlEncoded
    @POST("V1/removefromcart")
    Call<RemoveCartItemResponse> deleteCartItem(@Field("cart_id") String cart_id);

}
