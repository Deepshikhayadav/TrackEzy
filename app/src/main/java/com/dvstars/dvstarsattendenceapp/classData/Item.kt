package com.dvstars.dvstarsattendenceapp.classData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val className: String,
    @ColumnInfo(name = "section")
    val sectionName: String,
    @ColumnInfo(name = "teacher")
    val teacherName:String,


)
