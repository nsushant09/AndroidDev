package com.neupanesushant.learntesting

import android.content.Context

class ResourceComparer {
    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return string == context.getString(resId)
    }

}