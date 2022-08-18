package com.a7medkenawy.testroom.data.remote.models


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("hits")
    val imageResults: List<ImageResult?>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("totalHits")
    val totalHits: Int?
)