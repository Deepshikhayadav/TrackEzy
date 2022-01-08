package com.dvstars.dvstarsattendenceapp.classData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dvstars.dvstarsattendenceapp.studentData.Student
import com.dvstars.dvstarsattendenceapp.studentData.StudentDao

/**
 * Database class with a singleton INSTANCE object.
 */
@Database(entities = [Item::class, Student::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}