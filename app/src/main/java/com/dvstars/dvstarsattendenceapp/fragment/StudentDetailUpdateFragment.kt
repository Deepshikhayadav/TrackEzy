package com.dvstars.dvstarsattendenceapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dvstars.dvstarsattendenceapp.AttendanceApplication
import com.dvstars.dvstarsattendenceapp.R
import com.dvstars.dvstarsattendenceapp.StudentViewModel
import com.dvstars.dvstarsattendenceapp.StudentViewModelFactory
import com.dvstars.dvstarsattendenceapp.databinding.FragmentStudentDetailUpdateBinding
import com.dvstars.dvstarsattendenceapp.studentData.Student
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class StudentDetailUpdateFragment : Fragment() {
    private val navigationArgs: StudentDetailUpdateFragmentArgs by navArgs()
   // private val navArgs: ClassDetailFragmentArgs by navArgs()
    lateinit var student:Student

    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as AttendanceApplication).studentDatabase.studentDao()
        )
    }
    private var _binding: FragmentStudentDetailUpdateBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentDetailUpdateBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_student_detail_update, container, false)
    }
    /**
     * Binds views with the passed in item data.
     */
    private fun bind(student :Student) {
        binding.apply {
           // studentName.text=student.studentName
            studentName.setText(student.studentName)
            editAction.setOnClickListener { editItem(studentName) }
            delAction.setOnClickListener { showConfirmationDialog() }
        }
    }

    private fun delItem() {
        viewModel.deleteItem(student)
            findNavController().navigateUp()

    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                delItem()
            }
            .show()
    }
    /**
     * Navigate to the Edit item screen.
     */
    private fun editItem(studentName: TextInputEditText) {
        viewModel.updateItem(student.rollNo, studentName.text.toString(),navigationArgs.studentId)
        val action = StudentDetailUpdateFragmentDirections.actionStudentDetailUpdateFragmentToItemDetailFragment(
            student.rollNo
        )
        this.findNavController().navigate(action)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.studentId
        // Retrieve the item details using the itemId.
        // Attach an observer on the data (instead of polling for changes) and only update the
        // the UI when the data actually changes.
        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            student = selectedItem
            bind(student)
        }
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
