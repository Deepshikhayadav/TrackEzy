package com.dvstars.dvstarsattendenceapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvstars.dvstarsattendenceapp.AttendanceApplication
import com.dvstars.dvstarsattendenceapp.StudentViewModel
import com.dvstars.dvstarsattendenceapp.StudentViewModelFactory
import com.dvstars.dvstarsattendenceapp.adapter.ClassDetailFragmentAdapter

import com.dvstars.dvstarsattendenceapp.databinding.FragmentClassDetailBinding
import com.dvstars.dvstarsattendenceapp.studentData.Student

/**
 * [ClassDetailFragment] displays the details of the selected item.
 */
class ClassDetailFragment : Fragment() {

      private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as AttendanceApplication).studentDatabase.studentDao()
        )
    }
    private var _binding: FragmentClassDetailBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: ClassDetailFragmentArgs by navArgs()
    lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClassDetailFragmentAdapter{
            val action =
                ClassDetailFragmentDirections.actionItemDetailFragmentToStudentDetailUpdateFragment(it.rollNo)
            this.findNavController().navigate(action)
        }

        binding.recyclerViewStd.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewStd.adapter = adapter
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

        viewModel.allStudents.observe(this.viewLifecycleOwner) { students ->
            students.let {
                adapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ClassDetailFragmentDirections.actionItemDetailFragmentToAddStudentFragment("Add Student")
            this.findNavController().navigate(action)
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
