package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.home_notification_rv_card.view.*

class HomeNotificationAdapter(private val context : Context, private val titleList : ArrayList<String> , private val descList : ArrayList<String> )
    : RecyclerView.Adapter<HomeNotificationAdapter.ViewHolder>()
{
        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
            val cardItem  = view.home_noti_rv_card
            val titleItem = view.home_noti_rv_title
            val descItem = view.home_noti_rv_desc
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.home_notification_rv_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleItem.text = titleList.get(position)
        holder.descItem.text = descList.get(position)
    }

    override fun getItemCount(): Int {
        return titleList.size
    }
}