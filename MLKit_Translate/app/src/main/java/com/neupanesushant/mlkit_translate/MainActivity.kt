package com.neupanesushant.mlkit_translate

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.neupanesushant.mlkit_translate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countryCodes = hashMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCountriesData()

        binding.btnTranslate.setOnClickListener {
            translateExecution(binding.etInput.text.toString())
            binding.originalTv.text = binding.etInput.text
        }

        countryCodes.toSortedMap()
        countryCodes.forEach {
            Log.i("COUNTRY_CODES", "${it.key} : ${it.value}")
        }
    }

    private fun getCountriesData() {
        val jsonReader = JsonReader(this)
        jsonReader.readFromRawFile(R.raw.ml_countries) { jsonObject ->
            val languageName = jsonObject.getString("language_name")
            val languageCode = jsonObject.getString("language_code")
            countryCodes[languageName] = languageCode
        }
    }

    private fun translateExecution(text: String) {

        if (text.isEmpty()) {
            this.showToast("Empty field to convert")
            return
        }

        getTranslateClient().downloadModelIfNeeded(getModelDownloadConditions())
            .addOnSuccessListener {
                translate(text)
            }
            .addOnFailureListener {
                this.showToast(it.toString())
            }

    }

    private fun translate(text: String) {
        getTranslateClient().translate(text)
            .addOnSuccessListener {
                binding.translatedTv.text = it
            }
            .addOnFailureListener {
                this.showToast(it.toString())
            }
    }


    private fun getTranslateClient(): Translator {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.HINDI)
            .build()

        return Translation.getClient(options)
    }

    private fun getModelDownloadConditions(): DownloadConditions {
        return DownloadConditions.Builder()
            .requireWifi()
            .build()
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}