package com.example.learnrecyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learnrecyclerview.R
import kotlinx.android.synthetic.main.item_custom_row.view.*

class ItemAdapter (val context : Context, val items : ArrayList<String>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    //this is a default method create as soon as a viewholder is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return a view holder which is to be shown
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_custom_row,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //variable item that stores the value in items list current position
        val item = items.get(position)

        //setting the value of textview to item
        holder.tvItem.text = item

        //set background according to odd even position
        if(position%2==0){
            holder.cvItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,R.color.teal_200
                )
            )
        }else{
            holder.cvItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,R.color.teal_700
                )
            )
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvItem = view.rv_textView
        val cvItem = view.card_view_item
    }
}