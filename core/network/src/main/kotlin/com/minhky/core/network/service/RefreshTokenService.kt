package com.minhky.core.network.service

import com.minhky.core.network.model.request.RefreshTokenRequest
import com.minhky.core.network.model.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {

    @POST("refresh-token")
    fun refreshTokenAsync(
        @Body refreshToken: RefreshTokenRequest
    ): Call<AuthResponse>
}