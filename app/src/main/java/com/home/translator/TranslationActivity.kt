package com.home.translator

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.home.translator.service.CallResponse
import com.home.translator.service.ErrorResponse
import com.home.translator.service.TextTranslation


class TranslationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.editText1)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val sourceSpinner = findViewById<Spinner>(R.id.sourceSpinner)
        val translateButton = findViewById<Button>(R.id.translate)
        val textTranslation = TextTranslation()
        translateButton?.setOnClickListener {
            val targetSelectedLangauge = spinner.selectedItem.toString()
            val sourceSelectedLangauge = sourceSpinner.selectedItem.toString()
            if (sourceSelectedLangauge.equals("Choose Language for Translation")) {
                Toast.makeText(this@TranslationActivity, "Choose Language for Translation", Toast.LENGTH_LONG).show()
            } else if (targetSelectedLangauge.equals("Choose Language To Translate")){
                Toast.makeText(this@TranslationActivity, "Choose Language To Translation", Toast.LENGTH_LONG).show()
            } else {
                val languageToTranslate =
                    textTranslation.languageMapping().get(targetSelectedLangauge)
                val languageFromTranslate =
                    textTranslation.languageMapping().get(sourceSelectedLangauge)
                val json = textTranslation.callApi(
                    textView.text.toString(),
                    languageToTranslate.toString(),
                    languageFromTranslate.toString()
                )
                val gson = Gson()
                val response: CallResponse? = gson.fromJson(json, CallResponse::class.java)
                val translatedText = response?.data?.translations?.get(0)?.translatedText
                println("translatedText $translatedText")
                if (translatedText != null) {
                    Toast.makeText(this@TranslationActivity, translatedText, Toast.LENGTH_LONG).show()
                    textView2.setText(translatedText)
                } else {
                    val resp: ErrorResponse = gson.fromJson(json, ErrorResponse::class.java)
                    val status = resp.message
                    Toast.makeText(this@TranslationActivity, status, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
