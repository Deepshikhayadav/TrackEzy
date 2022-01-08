package com.dvstars.dvstarsattendenceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dvstars.dvstarsattendenceapp.databinding.ClassDetailsItemBinding
import com.dvstars.dvstarsattendenceapp.studentData.Student

class ClassDetailFragmentAdapter(private val onItemClicked: (Student) -> Unit) :  ListAdapter<Student, ClassDetailFragmentAdapter.ItemViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ClassDetailsItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ClassDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
           // binding.roll.text=student.rollNo.toString()
            binding.studentName.text = student.studentName
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentName == newItem.studentName
            }
        }
    }
}
