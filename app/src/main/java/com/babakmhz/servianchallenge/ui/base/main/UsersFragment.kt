package com.babakmhz.servianchallenge.ui.base.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.babakmhz.servianchallenge.R
import com.babakmhz.servianchallenge.ui.base.BaseFragment
import com.babakmhz.servianchallenge.utils.State
import kotlinx.android.synthetic.main.fragment_users_list.*
import timber.log.Timber


class UsersFragment : BaseFragment() {

    private lateinit var adapter: UsersRecyclerViewAdapter

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(MainViewModel::class.java)
    }

    override fun initializeViewModel() {
    }

    override fun initializeUI() {
        adapter = UsersRecyclerViewAdapter(arrayListOf()) {
            val action = UsersFragmentDirections.actionUsersFragmentToAblumFragment(it.id)
            findNavController().navigate(action)
        }
        rcl_users.adapter = adapter

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("savedInstanceState in UserFragment is $savedInstanceState")
        if (savedInstanceState == null)
            viewModel.getUsers()
        viewModel.usersLiveData.observe(viewLifecycleOwner, {
            when (it) {
                State.Loading -> {
                    showLoadingDialog(this::class.java.simpleName)
                }
                is State.Error -> showErrorSnackBar()
                is State.Success -> {
                    hideLoadingDialog(this::class.java.simpleName)
                    adapter.addData(it.data)
                }
                State.Idle ->{}
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UsersFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}