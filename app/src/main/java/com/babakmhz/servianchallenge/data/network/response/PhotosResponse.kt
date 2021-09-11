package com.babakmhz.servianchallenge.data.network.response

import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("") val photos: ArrayList<Photo>)

data class Photo(
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("albumId") val albumId: Long
)