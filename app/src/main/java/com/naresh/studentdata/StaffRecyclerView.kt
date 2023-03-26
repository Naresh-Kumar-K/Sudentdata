package com.naresh.studentdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naresh.studentdata.databinding.ListStaffBinding
import com.naresh.studentdata.stafflist.Staff

class StaffRecyclerView(): RecyclerView.Adapter<StaffRecyclerView.NewHolder>() {

    var staffList = emptyList<Staff>()
    private var onDeleteClick: ((Staff) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (Staff) -> Unit) {
        this.onDeleteClick = listener
    }

    class NewHolder(var binding: ListStaffBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(staff: Staff) {

                binding.apply {
                    tvUserName.text = staff.name
                    tvPassword.text = staff.password
                }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val view = ListStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewHolder(view)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        val list = staffList[position]
        holder.bind(list)
        holder.binding.ibDelete.setOnClickListener { onDeleteClick?.invoke(list) }
    }

    override fun getItemCount(): Int {
    return staffList.size
    }

    fun setData(staff: List<Staff>) {
        this.staffList = staff
        notifyDataSetChanged()
    }
}