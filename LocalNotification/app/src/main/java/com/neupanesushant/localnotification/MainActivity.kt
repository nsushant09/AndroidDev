package com.neupanesushant.localnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.neupanesushant.localnotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channelID"
    private lateinit var notificationIntent: PendingIntent
    private lateinit var binding: ActivityMainBinding
    private lateinit var builder : NotificationCompat.Builder

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        createNotificationChannel()

        // the following intent will take the user from notificatin tab to the specified activity
        notificationIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        setupNotification()

        binding.btnShowNotification.setOnClickListener{
            with(NotificationManagerCompat.from(this)){
                notify(9122, builder.build())
            }
        }
    }


    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "channelName"
            val description = "channelDescription"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
            }
            // register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    fun setupNotification() {
        builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("This is the title")
            .setContentText("This is the description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(notificationIntent)
            .setAutoCancel(true)

        // for longer longer i.e more lines than 1 you should use .setstyle().bigtext().


    }
}