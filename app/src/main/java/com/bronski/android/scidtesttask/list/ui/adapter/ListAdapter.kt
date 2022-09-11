package com.bronski.android.scidtesttask.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bronski.android.scidtesttask.core.utils.RecyclerItemListener
import com.bronski.android.scidtesttask.databinding.ItemListBinding

class ListAdapter(
    private val listener: RecyclerItemListener
) : PagingDataAdapter<DataItem, ListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemData = getItem(position) ?: return
        holder.bind(itemData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }


    inner class ListViewHolder(
        val binding: ItemListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemResultData: DataItem) = with(binding) {
            nameTextView.text = itemResultData.name
            itemView.setOnClickListener {
                listener.onItemClick(itemResultData)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}