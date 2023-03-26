package com.naresh.studentdata.stafflist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff_table")
data class Staff(
    var name: String,
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
