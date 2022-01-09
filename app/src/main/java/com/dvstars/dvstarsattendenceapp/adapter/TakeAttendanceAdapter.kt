package com.dvstars.dvstarsattendenceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dvstars.dvstarsattendenceapp.databinding.TakeAttendanceItemBinding
import com.dvstars.dvstarsattendenceapp.studentData.Student

class TakeAttendanceAdapter() :  ListAdapter<Student, TakeAttendanceAdapter.ItemViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            TakeAttendanceItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: TakeAttendanceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.studentName.text = student.studentName
            binding.checkbox.setOnClickListener {
                if(binding.checkbox.isChecked){
                    c++

                }
                else{
                    c--
                }
            }
        }
    }

    companion object {
        var c=0
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
