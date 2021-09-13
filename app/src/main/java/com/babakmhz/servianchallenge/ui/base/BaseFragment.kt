package com.babakmhz.servianchallenge.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babakmhz.servianchallenge.utils.LoadingContainer
import com.google.android.material.snackbar.Snackbar
import java.util.*

abstract class BaseFragment : Fragment(), LoadingContainer {
    private var loadingDialog: LoadingDialog? = null
    private val loadingIds = ArrayList<Int>()

    fun showErrorSnackBar() {
        Snackbar.make(requireView(), "Error occurred!", Snackbar.LENGTH_LONG).setAction("OK") {
        }.show()
    }

    override fun getLoadingDialog(): LoadingDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }
        return loadingDialog!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeViewModel()
    }

    abstract fun initializeViewModel()
    abstract fun initializeUI()

    fun <T : ViewModel> getSharedViewModel(viewModel: Class<T>): T {
        return ViewModelProvider(requireActivity()).get(viewModel)
    }


    override fun getLoadingIdsArray(): ArrayList<Int> {
        return loadingIds
    }

    override fun setLoadingDialogToNull() {
        loadingDialog = null
    }

}