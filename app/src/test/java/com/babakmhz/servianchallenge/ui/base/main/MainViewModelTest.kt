package com.babakmhz.servianchallenge.ui.base.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.babakmhz.servianchallenge.CoroutineTestRule
import com.babakmhz.servianchallenge.data.RepositoryHelper
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import com.babakmhz.servianchallenge.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {


    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(coroutineDispatcher)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var viewModel: MainViewModel
    private lateinit var repoHelper: RepositoryHelper

    @Before
    fun setup() {
        repoHelper = mockk()
        viewModel = MainViewModel(repoHelper)
//        viewModel.coroutineDispatcher = coroutineDispatcher
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `test getting data with error response should change live data state to error`() =
        coroutineTestRule.coroutineScope.runBlockingTest {
            val returnError = Throwable()
            coEvery { repoHelper.getData(ofType()) } throws (returnError)
            viewModel.usersLiveData.observeForever {
                println("users result $it $currentTime")
            }
            viewModel.getUsers()

            assertNotNull(viewModel.usersLiveData.value)
            assertEquals(viewModel.usersLiveData.value, State.Error(returnError))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getting data with success response should change live data state to success`() =
        coroutineTestRule.coroutineScope.runBlockingTest {

            val response = arrayListOf<UserResponse>()
            coEvery { repoHelper.getData(any()) } coAnswers {
                delay(100)
                firstArg<(ArrayList<UserResponse>)->Unit>().invoke(response)
            }

            viewModel.usersLiveData.observeForever {
                println("users result $it $currentTime")
            }
            viewModel.getUsers()

            assertNotNull(viewModel.usersLiveData.value)
            advanceTimeBy(0)
            assertEquals(viewModel.usersLiveData.value, State.Loading)
            advanceTimeBy(100)
            assertEquals(viewModel.usersLiveData.value, State.Success(response))
        }


    @ExperimentalCoroutinesApi
    @Test
    fun `test calling getUsers twice should not call api`() =
        coroutineTestRule.coroutineScope.runBlockingTest {

            viewModel.usersLiveData.observeForever { }

            coEvery { repoHelper.getData(any()) } coAnswers {
                firstArg<(ArrayList<UserResponse>)->Unit>().invoke(arrayListOf())
                delay(100)
            }
            // first time call
            viewModel.getUsers()

            //second time call
            viewModel.getUsers()
            repoHelper.getData {
            }

            // checking for idle state
            assertEquals(State.Idle, viewModel.usersLiveData.value)
        }
}