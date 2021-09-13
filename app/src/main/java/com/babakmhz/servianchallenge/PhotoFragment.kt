package com.babakmhz.servianchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.babakmhz.servianchallenge.ui.base.BaseFragment
import com.babakmhz.servianchallenge.utils.AppUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo.*


class PhotoFragment : BaseFragment() {

    private val args: PhotoFragmentArgs by navArgs()

    override fun initializeViewModel() {
        txt_albumId.text = getString(R.string.album_id_d, args.albumId.toInt())
        txt_photoId.text = getString(R.string.photo_id_d, args.photoId.toInt())
        txt_imageText.text = args.imageText

        AppUtils.LoadImageBindingAdapter.loadImage(imageView,args.imageUrl)

    }

    override fun initializeUI() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PhotoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}