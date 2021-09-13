package com.babakmhz.servianchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.servianchallenge.data.network.response.UserResponse
import com.babakmhz.servianchallenge.databinding.FragmentUsersBinding

class UsersRecyclerViewAdapter(
    private val items: ArrayList<UserResponse>,
    private val onUserClicked: (UserResponse) -> Unit
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

    fun addData(users: ArrayList<UserResponse>) {
        this.items.clear()
        this.items.addAll(users)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: FragmentUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val item_userContainer = binding.root.findViewById<CardView>(R.id.container)

        fun bind(item: UserResponse) {
            binding.repo = item
            binding.executePendingBindings()

            item_userContainer.setOnClickListener { onUserClicked(item) }

        }


    }

}