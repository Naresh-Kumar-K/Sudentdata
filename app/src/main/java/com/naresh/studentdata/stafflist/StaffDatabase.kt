package com.naresh.studentdata.stafflist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Staff::class], version = 1, exportSchema = false)
abstract class StaffDatabase : RoomDatabase(){

    abstract fun getStaffData(): StaffDao

    companion object {
        @Volatile
        var Instant: StaffDatabase? = null
        fun getDatabase(context: Context): StaffDatabase {
            val tempInstant = Instant
            if (tempInstant != null) return tempInstant
            synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    StaffDatabase::class.java,
                    "staff_table"
                ).build()
                Instant = instant
                return instant
            }
        }
    }
}