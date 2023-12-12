package com.neupanesushant.learnar

import android.content.Context
import android.widget.Toast

object Utils {
    fun Context.show(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}