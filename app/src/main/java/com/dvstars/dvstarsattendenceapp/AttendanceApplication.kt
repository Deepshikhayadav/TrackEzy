package com.dvstars.dvstarsattendenceapp
import android.app.Application
import com.dvstars.dvstarsattendenceapp.classData.ItemRoomDatabase


class AttendanceApplication : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
    val studentDatabase: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
