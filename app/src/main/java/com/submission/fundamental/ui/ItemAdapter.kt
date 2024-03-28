package com.submission.fundamental.ui
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.fundamental.R
import com.submission.fundamental.data.response.ItemsItem
import com.submission.fundamental.databinding.ItemListBinding


class ItemAdapter : ListAdapter<ItemsItem, ItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUser::class.java)
            intent.putExtra(DetailUser.KEY_NAME, user.login)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ItemViewHolder(private val binding:ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.tvName.text = user.type
            binding.tvUser.text = user.login
            Glide.with(itemView.context).load(user.avatarUrl).into(binding.imgAva)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
