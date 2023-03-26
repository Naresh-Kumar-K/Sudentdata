package com.naresh.studentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.naresh.studentdata.databinding.ActivityThirdBinding
import com.naresh.studentdata.stafflist.Staff
import com.naresh.studentdata.stafflist.StaffViewModel
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {

    lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: StaffViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third)
        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding.bSave.setOnClickListener {
            loadData()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadData() {

        binding.apply {
            val name = etUserName.text.toString()
            val password = etPassword.text.toString()
            val rePassword = etRePassword.text.toString()
            if (name.isBlank() || password.isBlank() || rePassword.isBlank()) {
                Toast.makeText(this@ThirdActivity, "Enter all Field!!",
                    Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (password == rePassword) {
                        val staff = Staff(name = name, password = password)
                        Toast.makeText(this@ThirdActivity, "Successfully Added!!",
                            Toast.LENGTH_SHORT).show()
                        viewModel.addStaff(staff)
                    } else
                        Toast.makeText(this@ThirdActivity, "Password is not same!!",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}