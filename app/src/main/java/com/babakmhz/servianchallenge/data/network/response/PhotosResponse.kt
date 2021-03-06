package com.babakmhz.servianchallenge.data.network.response

import com.google.gson.annotations.SerializedName


data class PhotosResponse(
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("albumId") val albumId: Long,
    @SerializedName("title") val title:String,
    @SerializedName("id") val id:Long
)