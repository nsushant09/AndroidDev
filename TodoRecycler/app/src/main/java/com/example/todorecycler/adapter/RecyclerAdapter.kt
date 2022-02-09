package com.example.todorecycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecycler.R
import kotlinx.android.synthetic.main.recycler_item_layout.view.*

class RecyclerAdapter (val context: Context,val namelist : ArrayList<String>,val agelist : ArrayList<Int>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           LayoutInflater.from(context).inflate(
               R.layout.recycler_item_layout,parent,false
           )
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = namelist.get(position)
        val age = agelist.get(position).toString()
        holder.nameItem.text = name
        holder.ageItem.text = age
    }

    override fun getItemCount(): Int {
        return namelist.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val cardItem = view.rv_cv
        val ageItem = view.rv_age
        val nameItem = view.rv_name
    }
}