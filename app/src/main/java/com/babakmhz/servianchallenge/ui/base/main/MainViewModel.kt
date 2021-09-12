package com.babakmhz.servianchallenge.ui.base.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babakmhz.servianchallenge.data.RepositoryHelper
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import com.babakmhz.servianchallenge.utils.State
import com.babakmhz.servianchallenge.utils.safeLaunch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repositoryHelper: RepositoryHelper) :
    ViewModel() {

    @VisibleForTesting
    var coroutineDispatcher = Dispatchers.IO

    private var _usersLiveData = MutableLiveData<State<ArrayList<UserResponse>>>()
    val usersLiveData: LiveData<State<ArrayList<UserResponse>>> = _usersLiveData

    fun getUsers() {
        viewModelScope.safeLaunch(_usersLiveData) {
            _usersLiveData.postValue(State.Loading)
            repositoryHelper.getData {
                _usersLiveData.postValue(State.Success(it))
            }
        }
    }
}