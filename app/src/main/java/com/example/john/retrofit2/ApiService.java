package com.example.john.retrofit2;

import java.util.List;
import java.util.Map;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by john on 2016/9/29.
 *
 */
public interface ApiService {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<ResponseBody>> getInfo(@Path("owner") String owner, @Path("repo") String repo);

    @GET("mobile/get")
    Call<Info> getInfo1(@Query("phone") String phone,@Query("key") String key);

    @GET("mobile/get")
    Call<Info> getInfo2(@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST("mobile/get")
    Call<Info> getInfo3(@Field("phone") String phone , @Field("key") String key);

    @FormUrlEncoded
    @POST("mobile/get")
    Call<Info> getInfo4(@FieldMap Map<String,String> map);
}
