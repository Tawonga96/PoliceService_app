package com.example.communityleader.Interface;

import com.example.communityleader.models.RegisterRequest;
import com.example.communityleader.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/v1/User/")
    Call<RegisterResponse> RegisterUser(@Body RegisterRequest registerRequest);
}
