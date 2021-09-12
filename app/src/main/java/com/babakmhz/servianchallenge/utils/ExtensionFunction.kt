package com.babakmhz.servianchallenge.utils

import android.view.View
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun View?.toVisible() {
    if (this?.visibility != View.VISIBLE)
        this?.visibility = View.VISIBLE
}

fun View?.toGone() {
    if (this?.visibility != View.GONE)
        this?.visibility = View.GONE
}

fun View?.toInvisible() {
    if (this?.visibility != View.INVISIBLE)
        this?.visibility = View.INVISIBLE
}


fun String?.validString() = this != null && this.isNotEmpty()


fun <T : Any> CoroutineScope.safeLaunch(
    liveData: MutableLiveData<State<T>>,
    coroutineBlock: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch(CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
        liveData.postValue(State.Error(throwable))
    }, block = coroutineBlock)

}

