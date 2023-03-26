package com.naresh.studentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.naresh.studentdata.databinding.ActivityMainBinding
import com.naresh.studentdata.stafflist.StaffViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StaffViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding.tvNewUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.apply {
            bLogin.setOnClickListener {
                val name = etUser.text.toString()
                val password = etPassword.text.toString()
                if (name.isNotBlank() && password.isNotBlank()) {
                    lifecycleScope.launch{
                        val user = viewModel.checkUser(name, password)
                        if (name == "admin" && password == "admin") {
                            val intent = Intent(this@MainActivity, StaffActivity::class.java)
                            startActivity(intent)
                        } else {
                            if (user == null) {
                                Toast.makeText(this@MainActivity,
                                    "In-Correct User or Password",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                val intent = Intent(this@MainActivity, SecondaryActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}
