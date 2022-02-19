package com.example.todorecycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecycler.R
import com.example.todorecycler.databinding.RecyclerItemLayoutBinding

class RecyclerAdapter (val context: Context,val namelist : ArrayList<String>,val agelist : ArrayList<Int>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           RecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

    class ViewHolder(view : RecyclerItemLayoutBinding) : RecyclerView.ViewHolder(view.root){
        val cardItem = view.rvCv
        val ageItem = view.rvAge
        val nameItem = view.rvName
    }
}