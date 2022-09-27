package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.RecyclerCardBinding
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random


class TodoAdapter(val context: Context, var list : List<HashMap<Todo, User>>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(), Filterable {

    val backupList = list

    var listFiltered = ArrayList<HashMap<Todo, User>>()

    inner class ViewHolder(view : RecyclerCardBinding) : RecyclerView.ViewHolder(view.root){
        val avatar = view.ivAvatar
        val name = view.tvName
        val email = view.tvEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todoObj = list.get(position).keys.elementAt(0)
        val userObj = list.get(position).values.elementAt(0)


        val random = Random.nextInt(1,6)
        when (random) {
            1 -> holder.avatar.setBackgroundResource(R.drawable.avatar_bg_blue)
            2 -> holder.avatar.setBackgroundResource(R.drawable.avatar_bg_cream)
            3 -> holder.avatar.setBackgroundResource(R.drawable.avatar_bg_green)
            4 -> holder.avatar.setBackgroundResource(R.drawable.avatar_bg_pink)
            5 -> holder.avatar.setBackgroundResource(R.drawable.avatar_bg_red)
        }

        val name = userObj.name
        val email = userObj.email

        holder.avatar.text = name.get(0).toString()
        holder.name.text = name.toString()
        holder.email.text = email.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val string = constraint?.toString() ?: ""

                if(string.isEmpty()) listFiltered = backupList as ArrayList<HashMap<Todo, User>>else {
                    val filteredList = ArrayList<HashMap<Todo, User>>()
                    list.filter{
                        (it.values.elementAt(0).name.contains(string)) or (it.values.elementAt(0).email.contains(string))
                    }
                        .forEach{filteredList.add(it)}
                    listFiltered = filteredList
                }
                return FilterResults().apply{values = listFiltered}
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = if(results?.values == null){
                    ArrayList()
                }else{
                    results.values as List<HashMap<Todo, User> /* = java.util.HashMap<com.neupanesushant.rxjava_subject_application.domain.Todo, com.example.retrofit.domain.User> */>
                }
                notifyDataSetChanged()
            }

        }
    }

}