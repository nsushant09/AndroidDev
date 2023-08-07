package com.neupanesushant.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExplicitBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Explicit Broadcast Reciever Triggered", Toast.LENGTH_SHORT).show()
    }
}