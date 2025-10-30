package com.daniel.myapp.app_tour.api

import com.daniel.myapp.app_tour.api.response.AuthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): AuthResponse
}