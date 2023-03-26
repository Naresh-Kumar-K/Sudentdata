package com.naresh.studentdata.studentslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Student>>
    private val repository: StudentRepository

    init{
        val studentDao = StudentDatabase.getDatabase(application).getStudentData()
        repository = StudentRepository(studentDao)
        readAllData = repository.readAllData
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(student)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(student)
        }
    }

}