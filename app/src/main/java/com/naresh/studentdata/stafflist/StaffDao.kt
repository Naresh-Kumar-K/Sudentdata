package com.naresh.studentdata.stafflist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StaffDao {

    @Insert
    suspend fun addStaff(staff: Staff)

    @Query("SELECT * FROM staff_table ORDER BY id ASC")
    fun readStaffData(): LiveData<List<Staff>>

    @Query("SELECT * FROM staff_table WHERE name = (:userName) AND password = (:password)")
    suspend fun checkUser(userName: String, password: String): Staff?

    @Delete
    suspend fun deleteStaff(staff: Staff)
}