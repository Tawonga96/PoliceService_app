package com.example.communityleader.Api;

import com.example.communityleader.Interface.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public  static Retrofit getRetrofit(){
        //Ongoing and Incoming request
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.8.102:8000/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public  static UserService GetService(){
        UserService userService = getRetrofit().create(UserService.class);
        return  userService;
    }
}
