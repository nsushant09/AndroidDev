package com.example.retrofit

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_card.view.*
import kotlin.coroutines.coroutineContext

class TodoAdapter(val context: Context, val list : List<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val idItem = view.rv_id
        val titleItem = view.rv_Title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = list.get(position).id
        val title = list.get(position).title

        holder.idItem.text = id.toString()
        holder.titleItem.text = title.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}