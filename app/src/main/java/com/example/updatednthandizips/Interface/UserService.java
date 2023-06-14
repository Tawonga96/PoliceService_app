package com.example.updatednthandizips.Interface;

import com.example.updatednthandizips.models.CasesRequest;
import com.example.updatednthandizips.models.CasesResponse;
import com.example.updatednthandizips.models.LoginRequest;
import com.example.updatednthandizips.models.LoginResponse;
import com.example.updatednthandizips.models.RegisterRequest;
import com.example.updatednthandizips.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface UserService {
    @POST("api/v1/User/login/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/api/v1/User/")
    Call<RegisterResponse> RegisterUser(@Body RegisterRequest registerRequest);

    @GET("/api/v1/Cases/")
    Call<CasesResponse> SendReport(@Body CasesRequest casesRequest);


}
