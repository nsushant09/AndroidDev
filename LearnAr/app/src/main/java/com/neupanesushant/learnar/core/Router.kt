package com.neupanesushant.learnar.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Router {
    protected val context: Context
    private var data = Bundle()

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, data: Bundle) {
        this.context = context
        this.data = data
    }

    private fun getIntentOrNull(action: Class<out AppCompatActivity>?): Intent? {
        if (action == null) {
            Log.e("Router", "Null action provided, Routing Stopped !!!")
            return null;
        }
        val intent = Intent(context, action)
        if (!data.isEmpty) {
            intent.putExtra("data", data)
        }
        return intent
    }

    private fun route(action: Class<out AppCompatActivity>?) {
        val intent = getIntentOrNull(action) ?: return
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    private fun routeNoHistory(action: Class<out AppCompatActivity>?) {
        val intent = getIntentOrNull(action) ?: return
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY
        context.startActivity(intent)
    }

    private fun routeForResult(action: Class<out AppCompatActivity>?, requestCode: Int) {
        val intent = getIntentOrNull(action) ?: return
        context.startActivity(intent)
    }

    private fun routeClearTask(action: Class<out AppCompatActivity>?) {
        val intent = getIntentOrNull(action) ?: return
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun route(container : Int, action : Class<out Fragment>){

    }
}