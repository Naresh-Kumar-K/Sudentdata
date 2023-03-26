package com.naresh.studentdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naresh.studentdata.databinding.ListStaffBinding
import com.naresh.studentdata.databinding.ListStudentBinding
import com.naresh.studentdata.stafflist.Staff
import com.naresh.studentdata.studentslist.Student

class StudentRecyclerView: RecyclerView.Adapter<StudentRecyclerView.NewHolders>() {
    var stdList = emptyList<Student>()
    var onClickListener: ((Student) -> Unit)? = null
    var onDeleteClickListener: ((Student) -> Unit)? = null

    fun setOnClicklistener(listener: (Student) -> Unit){
        this.onClickListener = listener
    }
/*
    fun setDeleteOnClicklistener(listener: (Student) -> Unit){
        this.onDeleteClickListener = listener
    }
*/

    class NewHolders(var binding: ListStudentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {

            binding.apply {
                tvStudentName.text = student.fullName
                tvFatherName.text = student.fatherName

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolders {
        val view = ListStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewHolders(view)
    }

    override fun onBindViewHolder(holder: NewHolders, position: Int) {
        val list = stdList[position]
        holder.bind(list)
        holder.itemView.setOnClickListener { onClickListener?.invoke(list) }
        //holder.itemView.setOnLongClickListener { true,onDeleteClickListener?.invoke(list) }
    }

    override fun getItemCount(): Int {
    return stdList.size
    }

    fun setData(student: List<Student>) {
        this.stdList = student
        notifyDataSetChanged()
    }
}