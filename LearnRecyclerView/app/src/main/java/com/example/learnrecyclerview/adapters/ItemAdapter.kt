package com.example.learnrecyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.learnrecyclerview.databinding.ItemCustomRowBinding

class ItemAdapter (val context : Context, val items : ArrayList<String>, val image: ArrayList<Int>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    //this is a default method create as soon as a viewholder is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return a view holder which is to be shown
        return ViewHolder(
            ItemCustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //variable item that stores the value in items list current position
        val item = items.get(position)
        val image = image.get(position)
        //setting the value of textview to item
        holder.tvItem.text = item
        holder.ivItem.setImageResource(image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(binding :  ItemCustomRowBinding) : RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.rvTextView
        val ivItem = binding.rvImageView
        val cvItem = binding.cardViewItem
    }
}