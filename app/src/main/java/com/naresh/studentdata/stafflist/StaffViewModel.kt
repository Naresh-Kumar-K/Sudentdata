package com.naresh.studentdata.stafflist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StaffViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Staff>>
    private val repository: StaffRepository

    init {
        val staffDao = StaffDatabase.getDatabase(application).getStaffData()
        repository = StaffRepository(staffDao)
        readAllData = repository.readData
    }

    fun addStaff(staff: Staff) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStaff(staff)
        }
    }

    suspend fun checkUser(userName: String, userPassword:String): Staff? {
        val result = viewModelScope.async {
            repository.checkUser(userName,userPassword)
        }
        return result.await()
    }

    fun delete(staff: Staff) {
        viewModelScope.launch {
            repository.deleteStaff(staff)
        }

}