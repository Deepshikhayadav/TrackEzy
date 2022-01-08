package com.dvstars.dvstarsattendenceapp.studentData

import androidx.room.*
import com.dvstars.dvstarsattendenceapp.classData.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * from student")
    fun getStudents() : Flow<List<Student>>

    @Query("SELECT * from student WHERE rollNo = :id")
    fun getStudent(id:Int) : Flow<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)
}