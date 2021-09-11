package com.babakmhz.servianchallenge.data

import com.babakmhz.servianchallenge.data.network.ApiHelper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RepositoryHelper {


}