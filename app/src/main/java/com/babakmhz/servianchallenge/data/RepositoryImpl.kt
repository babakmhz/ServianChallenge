package com.babakmhz.servianchallenge.data

import com.babakmhz.servianchallenge.data.network.ApiHelper
import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RepositoryHelper {

    private val photosWithOwner = hashMapOf<Long, ArrayList<PhotosResponse>>()

    private suspend fun findPhotosForOwner(
        users: ArrayList<UserResponse>,
        photos: ArrayList<PhotosResponse>
    ) {

//        for (user in users) {
//            val userPhotos = photos.filter { user.id == it.albumId }
//            photosWithOwner[user.id] = userPhotos as ArrayList<PhotosResponse>
//        }

        //manual way :
        var leftPointer = 0
        users.sortByDescending { it.id }
        photos.sortByDescending { it.albumId }
        while (leftPointer < photos.size && leftPointer < users.size) {
            val user = users[leftPointer]
            val photo = photos[leftPointer]
            if (user.id == photo.albumId) {
                if (photosWithOwner.containsKey(user.id))
                    photosWithOwner[user.id]?.add(photo)
                else
                    photosWithOwner[user.id] = arrayListOf(photo)
            }

            leftPointer++

        }


    }

    override suspend fun getAlbumForUserId(userId: Long): ArrayList<PhotosResponse>? =
        if (photosWithOwner.containsKey(userId)) photosWithOwner[userId] else arrayListOf()

    //users
    override suspend fun getData(users: (ArrayList<UserResponse>) -> Unit) {
        getUsers().zip(getPhotos()) { users, photos ->
            users(users)

        }

    }


    override suspend fun getUsers(): Flow<ArrayList<UserResponse>> {
        return apiHelper.getUsers()
    }

    override suspend fun getPhotos(): Flow<ArrayList<PhotosResponse>> {
        return apiHelper.getPhotos()
    }


}