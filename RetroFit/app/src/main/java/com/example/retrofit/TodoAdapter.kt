package com.example.retrofit

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.RecyclerCardBinding


class TodoAdapter(val context: Context, val list : List<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(view : RecyclerCardBinding) : RecyclerView.ViewHolder(view.root){
        val idItem = view.rvId
        val titleItem = view.rvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in)
        val id = list.get(position).id
        val title = list.get(position).title

        holder.idItem.text = id.toString()
        holder.titleItem.text = title.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}