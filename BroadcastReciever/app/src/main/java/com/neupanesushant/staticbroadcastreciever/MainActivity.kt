package com.neupanesushant.staticbroadcastreciever

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val dynamicBroadcastReciever = DynamicBroadcastReciever();
    private val customBroadcastReciever = CustomBroadcastReciever();

    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)

        //        Custom BroadCast Reciever
        val filter = IntentFilter(BroadcastManager.CUSTOM_ACTION)
        registerReceiver(customBroadcastReciever, filter)


    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(dynamicBroadcastReciever, filter)

        dynamicBroadcastReciever.onConnectivityChange = { isConnected ->
            if (isConnected) {
                tv.text = "Network Connected"
            } else {
                tv.text = "Network Not Connected"
            }
        }

        dynamicBroadcastReciever.onWifiChange = { isConnected ->
            if (isConnected) {
                tv.text = "WIFI Connected"
            } else {
                tv.text = "WIFI Disconnected"
            }
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(dynamicBroadcastReciever)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(customBroadcastReciever)
    }

}

/**
 * To call your custom broadcast reciever
 * Create a new app / or use another application
 * Then create a intentfilter using the broadcast action name i.e. com.neupanesushant.appname.CUSTOM_ACTION_NAME
 * Then add required values like string or any other data using
 * intent.putExtra("com.neupanesushant.appname.ACTION_VALUE", value)
 * sendBroadcast(intent)
 *
 * this will call the intent in your application
 *
 * To use broadcast in activity you can make private inner class and work
 * Or you can make use of callback
 *
 *
 * FOR EXPLICIT INTENT :
 *Create a broadcast receiver class
 * Then define the receiver in manifest file
 * Then go to the activity and create intent = Intent(activity, Class::class)
 * sendBroadcast(intent)
 *
 * To trigger the broadcast in antoher app / This will work if the another application is not working
 *
 * Create a app to send : The below code is in the app that sends
 * Create a app to recieve : In the reciever app create a class and use the class in manifest file and make exported true so that other app can access
 *
 * Intent intent = new Intent()
 * ComponentName cn = new ComponentName("com.neupanesushant.appname", "com.neupanesushant.appname.BroadcastRecieverClassName");
 * intent.setcomponent(cn)
 * sendBroadcast(intent)
 *
 * Another method
 * Intent intent = new Intent(action)
 * intent.setPackage("recieverapppackage")
 * sendBroadcast(intent)
 * In the reciever app add intent filter for the action in broadcast reciever
 *
 * If multiple application in the device has that action you can use
 *
 * PackageManager pm = getPackageManager()
 * List<ResolveInfo> infos = pm.queryBroadcastReceivers(intent, flag /0);
 * for(info : infos){
 *      ComponentName cn = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
 *      intent.setComponent(cn);
 *      sendBroadcast(intent);
 *  }
 *
 */
