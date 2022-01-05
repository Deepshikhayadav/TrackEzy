package com.dvstars.dvstarsattendenceapp.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

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
/**
 * Returns the passed in price in currency format.
 */
/*
fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(itemPrice)*/
