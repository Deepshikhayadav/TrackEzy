package com.dvstars.dvstarsattendenceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dvstars.dvstarsattendenceapp.data.Item

import com.dvstars.dvstarsattendenceapp.databinding.FragmentClassDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * [ClassDetailFragment] displays the details of the selected item.
 */
class ClassDetailFragment : Fragment() {
    /*private val navigationArgs: ClassDetailFragmentArgs by navArgs()
    lateinit var item: Item

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as AttendanceApplication).database.itemDao()
        )
    }
*/
    private var _binding: FragmentClassDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Binds views with the passed in item data.
     */
   /* private fun bind(item: Item) {
        binding.apply {
            itemName.text = item.className
            itemPrice.text = item.sectionName
            itemCount.text = item.teacherName
           // sellItem.isEnabled = viewModel.isStockAvailable(item)
           // sellItem.setOnClickListener { viewModel.sellItem(item) }
            deleteItem.setOnClickListener { showConfirmationDialog() }
            editItem.setOnClickListener { editItem() }
        }
    }

    *//**
     * Navigate to the Edit item screen.
     *//*
    private fun editItem() {
        val action = ClassDetailFragmentDirections.actionItemDetailFragmentToAddItemFragment(
            getString(R.string.edit_fragment_title),
            item.id
        )
        this.findNavController().navigate(action)
    }

    *//**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     *//*
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    *//**
     * Deletes the current item and navigates to the list fragment.
     *//*
    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.itemId
        // Retrieve the item details using the itemId.
        // Attach an observer on the data (instead of polling for changes) and only update the
        // the UI when the data actually changes.
        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item = selectedItem
            bind(item)
        }
    }

    *//**
     * Called when fragment is destroyed.
     *//*
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}
