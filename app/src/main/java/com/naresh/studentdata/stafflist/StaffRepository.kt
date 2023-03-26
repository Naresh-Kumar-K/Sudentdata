package com.naresh.studentdata.stafflist

import androidx.lifecycle.LiveData

class StaffRepository(private val staffDao: StaffDao) {
    val readData: LiveData<List<Staff>> = staffDao.readStaffData()

    suspend fun addStaff(staff: Staff){
        staffDao.addStaff(staff)
    }

    suspend fun checkUser(user: String, password: String): Staff?{
        return staffDao.checkUser(user, password)
    }

    suspend fun deleteStaff(staff: Staff){
        staffDao.deleteStaff(staff)
    }
}