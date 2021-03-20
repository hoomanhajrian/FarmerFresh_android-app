package com.super7.farmerfresh.network;

import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.network.model.ProductListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

//    // Auth SignUp
//    @POST("/V1/user/auth/reg")
//    Call<>

    // Get farms list
    @GET("V1/farms")
    Call<List<FarmListResponse>> getFarms();

    // Get product list
    @GET("V1/products")
    Call<List<ProductListResponse>> getProduct();
}
