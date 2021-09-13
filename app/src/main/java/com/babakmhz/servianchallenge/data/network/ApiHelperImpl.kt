package com.babakmhz.servianchallenge.data.network

import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun getUsers(): Flow<ArrayList<UserResponse>> {
        Timber.i("response from api ${apiService.getUsers()} $")
        return flowOf(apiService.getUsers())
    }

    override suspend fun getPhotos(): Flow<ArrayList<PhotosResponse>> {
        Timber.i("response from api ${apiService.getPhotos()} $")
        return flowOf(apiService.getPhotos())
    }

}