package com.babakmhz.servianchallenge.data

import com.babakmhz.servianchallenge.data.network.ApiHelper
import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse


interface RepositoryHelper : ApiHelper {
    suspend fun getAlbumForUserId(userId: Long): ArrayList<PhotosResponse>?
    suspend fun getData(users:(ArrayList<UserResponse>)->Unit)
}