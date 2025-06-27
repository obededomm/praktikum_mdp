package com.app.praktikum_kel1_2.service.api

import com.app.praktikum_kel1_2.model.request.LoginRequest
import com.app.praktikum_kel1_2.model.request.RegisterRequest
import com.app.praktikum_kel1_2.model.respon.LoginResponse
import com.app.praktikum_kel1_2.model.respon.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
