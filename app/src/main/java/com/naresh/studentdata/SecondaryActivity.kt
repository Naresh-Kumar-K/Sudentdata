package com.naresh.studentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.naresh.studentdata.databinding.ActivitySecondaryBinding
import com.naresh.studentdata.studentslist.StudentViewModel

class SecondaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondaryBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_secondary)
        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        adapter = StudentRecyclerView()
        binding.rvStd.layoutManager = GridLayoutManager(this,3)
        binding.rvStd.adapter = adapter
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

        viewModel.readAllData.observe(this, Observer { student ->
            adapter.setData(student)
        })
        adapter.setOnClicklistener{
            val intent = Intent(this,FourthActivity::class.java)
            intent.putExtra("Data",it)
            startActivity(intent)
        }
    }
}