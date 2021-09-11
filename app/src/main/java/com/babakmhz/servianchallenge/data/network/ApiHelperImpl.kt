package com.babakmhz.servianchallenge.data.network

import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun getUsers(): Flow<ArrayList<UserResponse>> {
        return flowOf(apiService.getUsers())
    }

    override suspend fun getPhotos(): Flow<ArrayList<PhotosResponse>> {
        return flowOf(apiService.getPhotos())
    }

}