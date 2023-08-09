package com.neupanesushant.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OrderedDynamicBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Ordered Dynamic Triggered", Toast.LENGTH_SHORT).show()
    }
}