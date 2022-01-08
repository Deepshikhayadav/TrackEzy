package com.dvstars.dvstarsattendenceapp

import androidx.lifecycle.*
import com.dvstars.dvstarsattendenceapp.classData.Item
import com.dvstars.dvstarsattendenceapp.classData.ItemDao
import com.dvstars.dvstarsattendenceapp.studentData.Student
import com.dvstars.dvstarsattendenceapp.studentData.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {
    val allStudents: LiveData<List<Student>> = studentDao.getStudents().asLiveData()
    /**
     * Updates an existing Item in the database.
     */
    fun updateItem(
        rollNo: Int,
        studentName:String,
        id:Int

    ) {
        val updatedItem = getUpdatedItemEntry(rollNo,studentName,id)
        updateItem(updatedItem)
    }


    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateItem(student: Student) {
        viewModelScope.launch {
            studentDao.update(student)
        }
    }



    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(studentName: String,id: Int) {
        val newItem = getNewItemEntry(studentName,id)
        insertItem(newItem)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
        }
    }

    /**
     * Launching a new coroutine to delete an item in a stn-blocking way
     */
    fun deleteItem(student: Student) {
        viewModelScope.launch {
            studentDao.delete(student)
        }
    }

    /**
     * Retrieve an item from the repository.
     */
    fun retrieveItem(id: Int): LiveData<Student> {
        return studentDao.getStudent(id).asLiveData()
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid( studentName: String): Boolean {
        if ( studentName.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(studentName: String,id:Int): Student {
        return Student(
            studentName=studentName,
            id=id
        )
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [Student] entity class with the item info updated by the user.
     */
    private fun getUpdatedItemEntry(
        rollNo: Int,
        studentName: String,
        id: Int
    ): Student {
        return Student(
            rollNo = rollNo,
            studentName=studentName,
            id=id
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class StudentViewModelFactory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

