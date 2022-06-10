package com.example.android.trackmysleepquality

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.SleepDetailsLayoutBinding

class SleepDetailsAdapter
    : RecyclerView.Adapter<SleepDetailsAdapter.ViewHolder>(){

    var list = listOf<SleepNight>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

        inner class ViewHolder(binding : SleepDetailsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
            val id = binding.tvNightId
            val startTime = binding.tvStartTime
            val endTime = binding.tvEndTime
            val quality = binding.tvSleepQuality
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SleepDetailsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list.get(position).nightId.toString()
        holder.startTime.text = list.get(position).startTimeMilli.toString()
        holder.endTime.text = list.get(position).endTimeMilli.toString()
        holder.quality.text = list.get(position).sleepQuality.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}