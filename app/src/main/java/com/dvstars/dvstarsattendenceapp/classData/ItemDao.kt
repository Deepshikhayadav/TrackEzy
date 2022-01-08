package com.dvstars.dvstarsattendenceapp.classData

import androidx.room.*
import com.dvstars.dvstarsattendenceapp.studentData.Student
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Inventory database
 */
@Dao
interface ItemDao {

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>



    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

/*
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM Item WHERE id = :id")
    suspend fun getClassWithStudents(id :Int) : Flow<List<ClassWithStudents>>
*/
}
