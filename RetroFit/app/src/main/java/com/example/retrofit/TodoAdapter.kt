package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.RecyclerCardBinding
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User


class TodoAdapter(val context: Context, val list : List<HashMap<Todo, User>>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(view : RecyclerCardBinding) : RecyclerView.ViewHolder(view.root){
        val idItem = view.rvId
        val titleItem = view.rvTitle
        val name = view.rvUsername
        val email = view.rvUseremail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todoObj = list.get(position).keys.elementAt(0)
        val userObj = list.get(position).values.elementAt(0)

        holder.itemView.animation = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in)
        val id = todoObj.id
        val title = todoObj.title
        val name = userObj.name
        val email = userObj.email

        holder.idItem.text = id.toString()
        holder.titleItem.text = title.toString()
        holder.name.text = name.toString()
        holder.email.text = email.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}