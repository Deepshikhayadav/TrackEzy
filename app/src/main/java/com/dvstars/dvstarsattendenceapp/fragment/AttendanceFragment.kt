package com.dvstars.dvstarsattendenceapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvstars.dvstarsattendenceapp.AttendanceApplication
import com.dvstars.dvstarsattendenceapp.AttendanceViewModel
import com.dvstars.dvstarsattendenceapp.AttendanceViewModelFactory
import com.dvstars.dvstarsattendenceapp.R
import com.dvstars.dvstarsattendenceapp.adapter.AttendanceAdapter
import com.dvstars.dvstarsattendenceapp.adapter.ClassListAdapter
import com.dvstars.dvstarsattendenceapp.databinding.ClassListFragmentBinding
import com.dvstars.dvstarsattendenceapp.databinding.ClassListItemBinding
import com.dvstars.dvstarsattendenceapp.databinding.FragmentAttendanceBinding
import com.dvstars.dvstarsattendenceapp.databinding.FragmentClassDetailBinding


class AttendanceFragment : Fragment() {
    private val viewModel: AttendanceViewModel by activityViewModels {
        AttendanceViewModelFactory(
            (activity?.application as AttendanceApplication).database.itemDao()
        )
    }

    private var _binding: FragmentAttendanceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttendanceBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AttendanceAdapter {
            val action =
                AttendanceFragmentDirections.actionAttendanceFragmentToTakeAttendanceFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.attRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.attRecyclerView.layoutParams= RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        binding.attRecyclerView.adapter = adapter
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }


    }
}
