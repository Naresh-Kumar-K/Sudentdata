package com.naresh.studentdata.studentslist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {

    abstract fun getStudentData(): StudentDao

    companion object {
        @Volatile
        var Instant: StudentDatabase? = null
        fun getDatabase(context: Context): StudentDatabase {
            val tempInstant = Instant
            if (tempInstant != null) return tempInstant
            synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_table"
                ).build()
                Instant = instant
                return instant
            }
        }
    }
}