package com.babakmhz.servianchallenge.data.network

import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import kotlinx.coroutines.flow.Flow


interface ApiHelper {
    suspend fun getUsers(): Flow<ArrayList<UserResponse>>
    suspend fun getPhotos(): Flow<ArrayList<PhotosResponse>>
}