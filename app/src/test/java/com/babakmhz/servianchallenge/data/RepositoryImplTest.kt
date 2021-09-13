package com.babakmhz.servianchallenge.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.babakmhz.servianchallenge.data.network.ApiHelper
import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryImplTest {

    private val ALBUMS_PER_USER = 100

    private lateinit var apiHelper: ApiHelper
    private lateinit var repoHelper : RepositoryImpl

    @get:Rule
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        apiHelper = mockk()
        repoHelper = RepositoryImpl(apiHelper)
    }

    @Test
    fun smokeTest() = assertTrue(true)

    private fun generateFakeUsers(): ArrayList<UserResponse> {
        val users = arrayListOf<UserResponse>()
        for (i in 0 until 20) {
            val user = UserResponse(i.toLong(),"","","")
            users.add(user)
        }
        return users
    }

    // generating 100 albums per user
    private fun generateFakeAlbums():ArrayList<PhotosResponse>{
        val photos = arrayListOf<PhotosResponse>()
        for(i in 0 until 20){
            for(j in 0 until ALBUMS_PER_USER){
                val photo = PhotosResponse("","",i.toLong(),"",i.toLong())
                photos.add(photo)
            }
        }
        return photos
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `test find photos for owner should work as expected manual way`() = runBlockingTest {
        val users = generateFakeUsers()
        val photos = generateFakeAlbums()
        repoHelper.findPhotosForOwner(users,photos)

        val photosWithOwner = (repoHelper.photosWithOwner)

        // as we generated 100 albums per user, we assert that
        for(user in users){
            assertEquals(photosWithOwner[user.id]?.size,ALBUMS_PER_USER)
        }
    }
}