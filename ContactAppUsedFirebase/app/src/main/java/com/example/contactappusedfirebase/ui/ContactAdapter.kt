package com.example.contactappusedfirebase.ui

import android.annotation.SuppressLint
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
            //recycler_view_contact.xml file is being binded to this adapter
            RecyclerViewContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //setting the recycler view cards views/elements
        holder.nameItem.text = contacts.get(position).fullName
        holder.numberItem.text = contacts.get(position).contactNumber
    }

    override fun getItemCount(): Int {
        //return the total cards to be displayed
        return contacts.size
    }

    inner class ViewHolder(val binding : RecyclerViewContactBinding) : RecyclerView.ViewHolder(binding.root){
        //reference to xml file views
        val nameItem = binding.tvName
        val numberItem = binding.tvContact
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addContact(contact : Contact){
        //when this method is called it checks if the contacts list contains the objects or not if it does not contains the object it then adds teh objects to the list
        if(!contacts.contains(contact)){
            contacts.add(contact)
        }
        //the change to the list is notified
        notifyDataSetChanged()
    }
}
