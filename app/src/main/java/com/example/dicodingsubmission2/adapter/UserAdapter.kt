package com.example.dicodingsubmission2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingsubmission2.data.model.DetailUserResponse
import com.example.dicodingsubmission2.databinding.ItemUserBinding


class UserAdapter(private val listUser: ArrayList<DetailUserResponse>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DetailUserResponse) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                tvItemUsername.text = user.login
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(ivItemPhoto)

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<DetailUserResponse>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])

    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailUserResponse)
    }
}