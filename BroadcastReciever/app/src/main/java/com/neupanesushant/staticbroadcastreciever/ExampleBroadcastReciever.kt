package com.neupanesushant.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * Steps
 * Create a new BroadcastReciever Class
 * Extend it with BroadcastReciever()
 * add the class in <reciever> in manifest
 * inside the <reciever> create a <intent-filter> tag
 * inside <intent-filter> add all the intents as <action> that is used in this class
 *
 *
 */
class ExampleBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            onBootCompleted(context, intent)
        }
    }

    private fun onBootCompleted(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Boot Completed", Toast.LENGTH_SHORT).show()
    }
}