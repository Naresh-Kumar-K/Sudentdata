package com.naresh.studentdata.studentslist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    suspend fun addStudent(student:Student)

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Student>>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)
}