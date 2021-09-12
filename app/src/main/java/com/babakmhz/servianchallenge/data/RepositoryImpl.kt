package com.babakmhz.servianchallenge.data

import androidx.annotation.VisibleForTesting
import com.babakmhz.servianchallenge.data.network.ApiHelper
import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepositoryHelper {

    @VisibleForTesting
    val photosWithOwner = hashMapOf<Long, ArrayList<PhotosResponse>>()

    @VisibleForTesting
    suspend fun findPhotosForOwner(
        users: ArrayList<UserResponse>,
        photos: ArrayList<PhotosResponse>
    ) {

        //the kotlin way
//        for (user in users) {
//            val userPhotos = photos.filter { user.id == it.albumId }
//            photosWithOwner[user.id] = userPhotos as ArrayList<PhotosResponse>
//        }


        // manual way
        users.sortBy { it.id }
        photos.sortBy { it.albumId }
        var userPointer = 0
        var photosPointer = 0

        while (userPointer < users.size && photosPointer < photos.size) {
            val user = users[userPointer]
            val photo = photos[photosPointer]
            if (user.id == photo.albumId) {
                if (photosWithOwner.containsKey(user.id)) photosWithOwner[user.id]?.add(photo) else photosWithOwner[user.id] =
                    arrayListOf(photo)
                photosPointer++
            } else
                userPointer++
        }

    }

    override suspend fun getAlbumForUserId(userId: Long): ArrayList<PhotosResponse>? =
        if (photosWithOwner.containsKey(userId)) photosWithOwner[userId] else arrayListOf()

    //users
    override suspend fun getData(users: (ArrayList<UserResponse>) -> Unit) {
        getUsers().zip(getPhotos()) { usersResponse, photosResponse ->
            users(usersResponse)
            findPhotosForOwner(usersResponse, photosResponse)
        }

    }


    override suspend fun getUsers(): Flow<ArrayList<UserResponse>> {
        return apiHelper.getUsers()
    }

    override suspend fun getPhotos(): Flow<ArrayList<PhotosResponse>> {
        return apiHelper.getPhotos()
    }


}