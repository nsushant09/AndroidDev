package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.classpackage.TodayAssignmentHome
import kotlinx.android.synthetic.main.fragment_student_home.view.*
import kotlinx.android.synthetic.main.home_assignment_rv_card.view.*
import kotlinx.android.synthetic.main.home_notification_rv_card.view.*

class HomeAssignmentAdapter(private val context : Context, private val list : ArrayList<TodayAssignmentHome>)
    : RecyclerView.Adapter<HomeAssignmentAdapter.ViewHolder>()
{
        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
            val cardItem = view.home_assign_rv_card
            val subItem = view.home_assign_rv_subject
            val descItem = view.home_assign_rv_desc
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.home_assignment_rv_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subItem.text = list.get(position).getSubject()
        holder.descItem.text = list.get(position).getAssignment()
    }

    override fun getItemCount(): Int = list.size
}