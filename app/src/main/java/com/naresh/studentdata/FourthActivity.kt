package com.naresh.studentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.naresh.studentdata.databinding.ActivityFourthBinding
import com.naresh.studentdata.studentslist.Student
import com.naresh.studentdata.studentslist.StudentViewModel
import kotlinx.coroutines.launch

class FourthActivity : AppCompatActivity() {

    lateinit var binding: ActivityFourthBinding
    private lateinit var viewModel: StudentViewModel
    private var std: Student? = null
    private lateinit var adapter: StudentRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fourth)
        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        adapter = StudentRecyclerView()

        std = intent.getSerializableExtra("Data") as? Student
        if (std == null) {
            Toast.makeText(this,"Invalid Login Id",Toast.LENGTH_SHORT).show()
        } else {
            binding.etStudentName.setText(std?.fullName.toString())
            binding.etFatherName.setText(std?.fatherName.toString())
            binding.etAge.setText(std?.age.toString())
            binding.etPhoneNumber.setText(std?.phoneNumber.toString())
            binding.etAddress.setText(std?.address.toString())
            binding.bSave.text = "Update"
        }
        binding.bSave.setOnClickListener {
            loadData()
            finish()
        }
    }

    private fun loadData() {

        binding.apply {
            val sName = etStudentName.text.toString()
            val fName = etFatherName.text.toString()
            val age = etAge.text.toString()
            val phone = etPhoneNumber.text.toString()
            val address = etAddress.text.toString()
            val gender = getData().toString()

            if (sName.isBlank() || fName.isBlank() || age.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this@FourthActivity, "Enter all Field!!",
                    Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (std == null) {
                        val student = Student(fullName = sName,
                            fatherName = fName,
                            age = age,
                            address = address,
                            gender = gender,
                            phoneNumber = phone)
                        Toast.makeText(this@FourthActivity, "Successfully Added!!",
                            Toast.LENGTH_SHORT).show()
                        viewModel.addStudent(student)
                    } else {
                        val student = Student(fullName = sName,
                            fatherName = fName,
                            age = age,
                            address = address,
                            gender = gender,
                            phoneNumber = phone)
                        student.id = std?.id ?: 0
                        viewModel.updateStudent(student)
                        finish()
                    }
                }
            }
        }
    }

    private fun getData(): String? {
        var data:String? = null

            if (binding.cbFemale.isPressed) {
                data = "Female"
            } else if (binding.cbMale.isPressed) {
               data = "Male"
            }else{
        Toast.makeText(this@FourthActivity,
            "Invalid Input", Toast.LENGTH_SHORT).show()
    }
        return data
    }
}