package com.example.mytask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static String baseurl = "https://dl.dropboxusercontent.com/s/p57gxwqm84zkp96/";

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static retrofit2.Retrofit retrofit;
    static retrofit2.Retrofit getRetrofit(){
        if(retrofit==null){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    return retrofit;
}
}