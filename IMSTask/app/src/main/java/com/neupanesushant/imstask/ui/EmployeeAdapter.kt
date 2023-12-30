package com.neupanesushant.imstask.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.imstask.R
import com.neupanesushant.imstask.data.model.IndiEmployee

class EmployeeAdapter(
    private val context: Context,
    private val employees: List<IndiEmployee>,
    private val onClickAction: (IndiEmployee) -> Unit
) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    private lateinit var tvUsername: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvSalary: TextView
    private lateinit var ivProfileImage: ImageView

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employees[position]

        tvUsername = holder.itemView.findViewById(R.id.tvUserName)
        tvAge = holder.itemView.findViewById(R.id.tvAge)
        tvSalary = holder.itemView.findViewById(R.id.tvSalary)
        ivProfileImage = holder.itemView.findViewById(R.id.ivProfileImage)


        tvUsername.text = employee.name
        tvAge.text = employee.age.toString()
        tvSalary.text = employee.salary.toString()

        holder.itemView.setOnClickListener { onClickAction(employee) }
    }


}