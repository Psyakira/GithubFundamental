package com.submission.fundamental.ui
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
    }

    class ItemViewHolder(private val binding:ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.tvName.text = user.login
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

//class ItemAdapter(private val users: List<ItemsItem>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
//        return ItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        val user = users[position]
//        holder.bind(user)
//    }
//
//    override fun getItemCount() = users.size
//
//    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(user: ItemsItem) {
//            itemView.apply {
//                binding.tvItem.text = user.login
//                Glide.with(this)
//                    .load(user.avatarUrl)
//                    .placeholder(R.drawable.placeholder_image) // Placeholder image jika avatar belum terload
//                    .error(R.drawable.error_image) // Image error jika gagal load avatar
//                    .into(avatarImageView)
//            }
//        }
//    }
//}

