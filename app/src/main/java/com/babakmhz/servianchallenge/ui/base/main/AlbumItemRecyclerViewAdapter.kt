package com.babakmhz.servianchallenge.ui.base.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.servianchallenge.R
import com.babakmhz.servianchallenge.data.network.response.PhotosResponse
import com.babakmhz.servianchallenge.databinding.FragmentAlbumBinding


class AlbumItemRecyclerViewAdapter(
    private val items: ArrayList<PhotosResponse>,
    private val onAlbumClicked: (PhotosResponse) -> Unit
) : RecyclerView.Adapter<AlbumItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun addData(photos: ArrayList<PhotosResponse>) {
        this.items.clear()
        this.items.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: FragmentAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val item_userContainer = binding.root.findViewById<CardView>(R.id.container)
        fun bind(item: PhotosResponse) {
            binding.repo = item
            binding.executePendingBindings()
            item_userContainer.setOnClickListener { onAlbumClicked(item) }

        }
    }

}