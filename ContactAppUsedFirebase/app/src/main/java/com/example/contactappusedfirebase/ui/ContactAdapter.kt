package com.example.contactappusedfirebase.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappusedfirebase.R
import com.example.contactappusedfirebase.data.Contact
import com.example.contactappusedfirebase.databinding.RecyclerViewContactBinding

class ContactAdapter() : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    //list of contact object
    var contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(val binding : RecyclerViewContactBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
