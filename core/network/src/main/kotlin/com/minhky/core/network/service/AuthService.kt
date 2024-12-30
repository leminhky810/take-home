package com.minhky.core.network.service

import com.minhky.core.network.model.request.LoginRequest
import com.minhky.core.network.model.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    fun login(
        @Body body: LoginRequest
    ): Call<AuthResponse>

}