package com.babakmhz.servianchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.servianchallenge.databinding.FragmentUsersBinding
import com.babakmhz.servianchallenge.placeholder.PlaceholderContent.PlaceholderItem

class UsersRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUsersBinding) : RecyclerView.ViewHolder(binding.root) {


    }

}