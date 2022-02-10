package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.menu_recycler_view_card.view.*

class MenuRecyclerAdapter(val context : Context, val imageList : ArrayList<Int> , val titleList : ArrayList<String>, val descriptionList : ArrayList<String> )
    : RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.menu_recycler_view_card,parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imageList.get(position)
        val title = titleList.get(position)
        val description = descriptionList.get(position)

        holder.imageItem.setImageResource(image)
        holder.titleItem.text = title
        holder.descriptionItem.text = description

        //onclick listener for recycler view Item
        holder.cardItem.setOnClickListener{
            Toast.makeText(context,"Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val cardItem = view.menu_rv_card
        val imageItem = view.menu_rv_image
        val titleItem = view.menu_rv_title
        val descriptionItem = view.menu_rv_description
    }
}
