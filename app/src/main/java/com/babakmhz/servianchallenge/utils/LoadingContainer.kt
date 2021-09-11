package com.babakmhz.servianchallenge.utils

import com.babakmhz.servianchallenge.ui.base.LoadingDialog
import timber.log.Timber
import java.util.*

interface LoadingContainer {
    fun getLoadingDialog(): LoadingDialog?
    fun getLoadingIdsArray(): ArrayList<Int>
    fun setLoadingDialogToNull()

    fun showLoadingDialog(
        stringId: String, cancelable: Boolean = false, onCancel: () -> Unit = {}
    ) {
        Timber.e("Loading dialog show : $stringId")
        val id = stringId.hashCode()
        if (getLoadingIdsArray().contains(id)) {
            return
        }
        getLoadingIdsArray().add(id)
        if (getLoadingIdsArray().size == 1) {
            val loadingDialog = getLoadingDialog()
            loadingDialog?.setCancelable(cancelable)
            loadingDialog?.setOnCancelListener {
                onCancel()
            }
            loadingDialog?.show()
        }
    }

    fun hideLoadingDialog(stringId: String) {
        Timber.e("Loading dialog hide : $stringId")
        val id = stringId.hashCode()
        getLoadingIdsArray().remove(id)
        if (getLoadingIdsArray().size == 0) {
            val loadingDialog: LoadingDialog? = getLoadingDialog()
            if (loadingDialog != null) {
                loadingDialog.dismiss()
                setLoadingDialogToNull()
            }
        }
    }
}