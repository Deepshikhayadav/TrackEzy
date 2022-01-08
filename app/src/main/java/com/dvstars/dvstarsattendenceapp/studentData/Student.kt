package com.dvstars.dvstarsattendenceapp.studentData

import androidx.room.*
import com.dvstars.dvstarsattendenceapp.classData.Item

@Entity
data class Student (
    @PrimaryKey(autoGenerate = true)
    val rollNo:Int=0,
    @ColumnInfo(name = "student")
    val studentName:String,
    val id:Int,

)