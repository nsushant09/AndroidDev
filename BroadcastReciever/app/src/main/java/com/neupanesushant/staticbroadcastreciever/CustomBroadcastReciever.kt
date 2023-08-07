package com.neupanesushant.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (BroadcastManager.CUSTOM_ACTION.equals(intent?.action)) {
            val recievedString = intent?.getStringExtra(BroadcastManager.CUSTOM_ACTION_STRING)
            Toast.makeText(context, recievedString, Toast.LENGTH_SHORT).show()
        }
    }
}