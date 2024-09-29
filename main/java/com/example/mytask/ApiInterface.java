package com.example.mytask;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("demo_api.json")
    Call<Pojo> getData();
}
