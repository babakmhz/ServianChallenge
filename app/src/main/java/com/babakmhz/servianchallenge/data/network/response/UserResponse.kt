package com.babakmhz.servianchallenge.data.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id:Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String
)