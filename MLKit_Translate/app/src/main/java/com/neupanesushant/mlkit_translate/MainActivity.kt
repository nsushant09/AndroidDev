package com.neupanesushant.mlkit_translate

import android.content.Context
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnTranslate.setOnClickListener {
            translateExecution(binding.etInput.text.toString())
            binding.originalTv.text = binding.etInput.text
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