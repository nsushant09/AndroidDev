package com.neupanesushant.twoviewtyperecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.twoviewtyperecyclerview.databinding.FromLayoutBinding
import com.neupanesushant.twoviewtyperecyclerview.databinding.ToLayoutBinding

class CustomAdapter(val context : Context, val list : List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    internal val FROM = 1
    internal val TO = 2

    private inner class ViewHolderFrom (binding : FromLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        var message : TextView

        init{
            message = binding.tvMessage
        }

        internal fun bind(position: Int){
            message.text = list.get(position).messageBody
        }
    }

    private inner class ViewHolderTo (binding: ToLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        var message : TextView

        init{
            message = binding.tvMessage
        }
        internal fun bind(position: Int){
            message.text = list.get(position).messageBody
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == FROM){
            ViewHolderFrom(
                FromLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }else{
            ViewHolderTo(
                ToLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(list.get(position).fromUid.equals("1")){
            (holder as ViewHolderFrom).bind(position)
        }else{
            (holder as ViewHolderTo).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if(list.get(position).fromUid.equals("1")){
            return FROM
        }else{
            return TO
        }
    }


}