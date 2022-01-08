package com.dvstars.dvstarsattendenceapp.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dvstars.dvstarsattendenceapp.AttendanceApplication
import com.dvstars.dvstarsattendenceapp.AttendanceViewModel
import com.dvstars.dvstarsattendenceapp.AttendanceViewModelFactory
import com.dvstars.dvstarsattendenceapp.classData.Item
import com.dvstars.dvstarsattendenceapp.databinding.FragmentAddClassBinding


/**
 * Fragment to add or update an item in the Inventory database.
 */
class AddClassFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val viewModel: AttendanceViewModel by activityViewModels {
        AttendanceViewModelFactory(
            (activity?.application as AttendanceApplication).database
                .itemDao()
        )
    }
    private val navigationArgs: ClassDetailFragmentArgs by navArgs()

    lateinit var item: Item

    // Binding object instance corresponding to the fragment_add_item.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment
    private var _binding: FragmentAddClassBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater : LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.className.text.toString(),
            binding.sectionName.text.toString(),
            binding.teacherName.text.toString(),
        )
    }

    /**
     * Binds views with the passed in [item] information.
     */
    private fun bind(item: Item) {
       // val price = "%.2f".format(item.itemPrice)
        binding.apply {
            className.setText(item.className, TextView.BufferType.SPANNABLE)
            sectionName.setText(item.sectionName, TextView.BufferType.SPANNABLE)
            teacherName.setText(item.teacherName, TextView.BufferType.SPANNABLE)
            saveAction.setOnClickListener { updateItem() }
        }
    }

    /**
     * Inserts the new Item into database and navigates up to list fragment.
     */
    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                binding.className.text.toString(),
                binding.sectionName.text.toString(),
                binding.teacherName.text.toString(),
            )
            val action = AddClassFragmentDirections.actionAddItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }

    /**
     * Updates an existing Item in the database and navigates up to list fragment.
     */
    private fun updateItem() {
        if (isEntryValid()) {
            viewModel.updateItem(
                this.navigationArgs.itemId,
                this.binding.className.text.toString(),
                this.binding.sectionName.text.toString(),
                this.binding.teacherName.text.toString()
            )
            val action = AddClassFragmentDirections.actionAddItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }

    /**
     * Called when the view is created.
     * The itemId Navigation argument determines the edit item  or add new item.
     * If the itemId is positive, this method retrieves the information from the database and
     * allows the user to update it.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId
        if (id > 0) {
            viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
                item = selectedItem
                bind(item)
            }
        } else {
            binding.saveAction.setOnClickListener {
                addNewItem()
            }
        }
    }

    /**
     * Called before fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
