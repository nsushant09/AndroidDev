package com.neupanesushant.mlkit_translate

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class JsonReader(private val context: Context) {

    fun readFromRawFile(rawResourceId: Int, parser: (JSONObject) -> Unit) {
        val inputStream: InputStream = context.resources.openRawResource(rawResourceId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            parser(jsonObject)
        }
    }


}