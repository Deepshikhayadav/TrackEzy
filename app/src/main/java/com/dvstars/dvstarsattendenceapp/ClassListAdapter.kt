package com.dvstars.dvstarsattendenceapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dvstars.dvstarsattendenceapp.data.Item
import com.dvstars.dvstarsattendenceapp.databinding.ClassListItemBinding


/**
 * [ListAdapter] implementation for the recyclerview.
 */

class ClassListAdapter(/*private val onItemClicked: (Item) -> Unit*/) :
    ListAdapter<Item, ClassListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ClassListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
       /* holder.itemView.setOnClickListener {
            onItemClicked(current)
        }*/
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ClassListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.className.text = item.className
            binding.section.text = item.sectionName
            binding.teacherName.text = item.teacherName
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.className == newItem.className
            }
        }
    }
}
