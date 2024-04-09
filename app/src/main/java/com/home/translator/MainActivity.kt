package com.home.translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.home.translator.trans.CallResponse
import com.home.translator.trans.TextTranslation

class MainActivity : AppCompatActivity() {
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
                Toast.makeText(this@MainActivity, "Choose Language for Translation", Toast.LENGTH_LONG).show()
            } else if (targetSelectedLangauge.equals("Choose Language To Translate")){
                Toast.makeText(this@MainActivity, "Choose Language To Translation", Toast.LENGTH_LONG).show()
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
                // displaying a toast message
                Toast.makeText(this@MainActivity, translatedText, Toast.LENGTH_LONG).show()
                textView2.setText(translatedText)
            }
        }
    }
}
