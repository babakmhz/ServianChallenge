package com.babakmhz.servianchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.babakmhz.servianchallenge.ui.base.BaseFragment
import com.babakmhz.servianchallenge.ui.base.main.MainViewModel
import com.babakmhz.servianchallenge.utils.State
import kotlinx.android.synthetic.main.fragment_ablum_list.*
import timber.log.Timber

class AlbumFragment : BaseFragment() {

    val args: AlbumFragmentArgs by navArgs()

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(MainViewModel::class.java)
    }

    private lateinit var adapter: AlbumItemRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.albumsOfUser.observe(viewLifecycleOwner, {
            when (it) {
                State.Loading -> {
                    showLoadingDialog(this::class.java.simpleName)
                }
                is State.Error -> showErrorSnackBar()
                is State.Success -> {
                    Timber.i("got data ${it.data}")
                    hideLoadingDialog(this::class.java.simpleName)
                    adapter.addData(it.data)
                }
            }
        })
    }

    override fun initializeViewModel() {
        Timber.i("args of albumFragment $args")
        viewModel.getAlbumsOfUser(args.userId)
    }

    override fun initializeUI() {
        adapter = AlbumItemRecyclerViewAdapter(arrayListOf()) {
            val action = AlbumFragmentDirections.actionAblumFragmentToPhotoFragment(
                it.albumId, it.id, it.title,it.url
            )
            findNavController().navigate(action)
        }
        rcl_album.adapter = adapter
        txt_header.apply {
            text = getString(R.string.album_id_d,args.userId.toInt())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ablum_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AlbumFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}