package com.naresh.studentdata.studentslist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "student_table")
data class Student(

    var fullName: String,
    var fatherName: String,
    var gender: String,
    var age: String,
    var address: String,
    var phoneNumber: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
