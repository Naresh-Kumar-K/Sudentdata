package com.naresh.studentdata.studentslist

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {

    val readAllData: LiveData<List<Student>> = studentDao.readAllData()

    suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun delete(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun update(student: Student){
        studentDao.updateStudent(student)
    }
}