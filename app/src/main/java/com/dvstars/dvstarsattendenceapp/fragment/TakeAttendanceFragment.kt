package com.dvstars.dvstarsattendenceapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvstars.dvstarsattendenceapp.AttendanceApplication
import com.dvstars.dvstarsattendenceapp.R
import com.dvstars.dvstarsattendenceapp.StudentViewModel
import com.dvstars.dvstarsattendenceapp.StudentViewModelFactory
import com.dvstars.dvstarsattendenceapp.adapter.ClassDetailFragmentAdapter
import com.dvstars.dvstarsattendenceapp.adapter.TakeAttendanceAdapter
import com.dvstars.dvstarsattendenceapp.databinding.FragmentClassDetailBinding
import com.dvstars.dvstarsattendenceapp.databinding.FragmentTakeAttendanceBinding
import com.dvstars.dvstarsattendenceapp.studentData.Student


class TakeAttendanceFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as AttendanceApplication).studentDatabase.studentDao()
        )
    }
    private var _binding: FragmentTakeAttendanceBinding? = null
    private val binding get() = _binding!!
   // private val navigationArgs: ClassDetailFragmentArgs by navArgs()
    lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTakeAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TakeAttendanceAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

        viewModel.allStudents.observe(this.viewLifecycleOwner) { students ->
            students.let {
                adapter.submitList(it)
            }
        }

    }

    /*
     * Called when fragment is destroyed.
    */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
