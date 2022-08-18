package com.a7medkenawy.testroom.data.remote

import com.a7medkenawy.testroom.BuildConfig
import com.a7medkenawy.testroom.data.remote.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixapayAPI {

    @GET("/api/")
    fun getImages(
        @Query("q") searchQuery: String,
        @Query("key") API_KEY: String = BuildConfig.API_KEY,
    ): Response<ImageResponse>
}