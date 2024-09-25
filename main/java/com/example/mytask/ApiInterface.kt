package com.example.mytask

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("demo_api.json")
    fun getData() : Call<List<Result>>
}