package com.babakmhz.servianchallenge.data.network

import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import com.babakmhz.servianchallenge.utils.PHOTOS_ENDPOINT
import com.babakmhz.servianchallenge.utils.USERS_ENDPOINT
import retrofit2.http.GET

interface ApiService {

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): ArrayList<UserResponse>

    @GET(PHOTOS_ENDPOINT)
    suspend fun getPhotos(): ArrayList<PhotosResponse>
}