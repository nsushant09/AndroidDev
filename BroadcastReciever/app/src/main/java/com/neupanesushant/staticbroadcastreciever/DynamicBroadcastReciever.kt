package com.neupanesushant.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

class DynamicBroadcastReciever : BroadcastReceiver() {

    var onConnectivityChange: (Boolean) -> Unit = {}
    var onWifiChange: (Boolean) -> Unit = {}
    override fun onReceive(context: Context?, intent: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent?.action) {
            onConnectivityChange(context, intent)
        }

        if (WifiManager.WIFI_STATE_CHANGED_ACTION == intent?.action) {
            onWifiChange(context, intent)
        }

    }

    private fun onWifiChange(context: Context?, intent: Intent?) {

        when (intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)) {
            WifiManager.WIFI_STATE_ENABLED -> {
                onWifiChange(true)
            }

            WifiManager.WIFI_STATE_DISABLED -> {
                onWifiChange(false)
            }
        }
    }

    private fun onConnectivityChange(context: Context?, intent: Intent?) {
        val noConnectivity = intent?.getBooleanExtra(
            ConnectivityManager.EXTRA_NO_CONNECTIVITY,
            false
        )

        noConnectivity?.let { isNotConnected ->
            onConnectivityChange.let {
                it(!isNotConnected)
            }
        }
    }
}
