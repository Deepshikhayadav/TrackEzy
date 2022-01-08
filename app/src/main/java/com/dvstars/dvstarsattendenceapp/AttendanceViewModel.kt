package com.dvstars.dvstarsattendenceapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dvstars.dvstarsattendenceapp.classData.Item
import com.dvstars.dvstarsattendenceapp.classData.ItemDao
import com.dvstars.dvstarsattendenceapp.studentData.Student
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class AttendanceViewModel(private val itemDao: ItemDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    /*fun oneToMany(itemId: Int){
        val classWithStud :LiveData<List<Student>> =getManyStudents(itemId)
    }
    private fun getManyStudents(className: String, sectionName: String, teacherName: String): Item {
        return Item(
            className=className,
            sectionName = sectionName,
            teacherName = teacherName
        )
    }

    private fun getStudents(itemId: Int): LiveData<List<Student>> {
        viewModelScope.launch {
          itemDao.getClassWithStudents(itemId).asLiveData()
        }
    }
*/
    /**
     * Updates an existing Item in the database.
     */
    fun updateItem(
        itemId: Int,
        className: String,
        sectionName: String,
        teacherName: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, className,sectionName,teacherName)
        updateItem(updatedItem)
    }


    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }



    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(className: String, sectionName: String, teacherName: String) {
        val newItem = getNewItemEntry(className,sectionName,teacherName)
        insertItem(newItem)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    /**
     * Launching a new coroutine to delete an item in a non-blocking way
     */
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    /**
     * Retrieve an item from the repository.
     */
    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(className: String, sectionName: String, teacherName: String): Boolean {
        if (className.isBlank() || sectionName.isBlank() || teacherName.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(className: String, sectionName: String, teacherName: String): Item {
        return Item(
            className=className,
            sectionName = sectionName,
            teacherName = teacherName
        )
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [Item] entity class with the item info updated by the user.
     */
    private fun getUpdatedItemEntry(
        itemId: Int,
       className: String,
        sectionName: String,
        teacherName: String
    ): Item {
        return Item(
            id = itemId,
           className=className,
            sectionName=sectionName,
            teacherName=teacherName
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class AttendanceViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendanceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttendanceViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

