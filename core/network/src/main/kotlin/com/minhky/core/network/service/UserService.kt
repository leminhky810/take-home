package com.minhky.core.network.service

import com.minhky.core.network.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users")
    fun getList(
        @Query(value = "since") page: Long,
        @Query(value = "per_page") limit: Int
    ): Call<List<UserResponse>>


    @GET("users/{userName}")
    fun getProfile(
        @Path(value = "userName") userName: String
    ): Call<UserResponse>

}