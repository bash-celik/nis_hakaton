package com.gengar.nishakaton.network;

import com.gengar.nishakaton.pojo.PostPump;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @POST("near")
    @FormUrlEncoded
    Call<List<PostPump>> postPump(@Field("id") String id,
                                  @Field("stationId")String stationId);
}
