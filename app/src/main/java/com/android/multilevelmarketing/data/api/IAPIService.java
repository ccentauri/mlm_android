package com.android.multilevelmarketing.data.api;

import com.android.multilevelmarketing.data.model.RegisterResponse;
import com.android.multilevelmarketing.data.model.ServerPublicKey;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAPIService {
    @FormUrlEncoded
    @POST("login")
    Call<RegisterResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("rsa/public")
    Call<ServerPublicKey> getServerPubKey();
}