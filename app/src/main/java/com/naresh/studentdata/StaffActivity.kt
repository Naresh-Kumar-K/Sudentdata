package com.naresh.studentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naresh.studentdata.databinding.ActivityStaffBinding
import com.naresh.studentdata.stafflist.StaffViewModel

class StaffActivity : AppCompatActivity() {
    lateinit var binding: ActivityStaffBinding
    lateinit var adapter: StaffRecyclerView
    lateinit var viewModel: StaffViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff)
        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding.rrOne.layoutManager = LinearLayoutManager(this)
        adapter = StaffRecyclerView()
        binding.rrOne.adapter = adapter
        viewModel.readAllData.observe(this, Observer { staff ->
            adapter.setData(staff)
        })
        adapter.setOnDeleteClickListener {
            viewModel.delete(it)
        }
    }
}