package com.home.translator.service

import okhttp3.*
import okio.IOException

class TextTranslation {
    fun callApi(text: String, language: String, sourcelanguage: String): String? {
        val url = "https://google-translate1.p.rapidapi.com/language/translate/v2"
        var finalResponse: String? = null
        // add parameter
        val formBody = FormBody.Builder()
            .add("q", text)
            .add("target", language)
            .add("source", sourcelanguage)
            .build()

        // creating request
        val request = Request.Builder().url(url)
            .post(formBody)
            .header("X-RapidAPI-Key", "4232c58856mshdbe9edd460297c5p1bdbfdjsn1e25b3a347ba")
            .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
            .build()

        val client = OkHttpClient();
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                finalResponse = response.body?.string()
                println(finalResponse)
            }

            override fun onFailure(call: Call, e: IOException) {
                finalResponse = e.message.toString()
                println(finalResponse)
            }
        })
        println("Text Translation Started")
        Thread.sleep(5000)
        return finalResponse
    }

    fun languageMapping(): HashMap<String, String> {
        val languageMap = hashMapOf<String, String>()
        languageMap.put("English", "en")
        languageMap.put("Telugu", "te")
        languageMap.put("Hindi", "hi")
        languageMap.put("German", "de")
        languageMap.put("Italian", "it")
        languageMap.put("French", "fr")
        languageMap.put("Tamil", "ta")
        return languageMap
    }
}

data class Translation(val translatedText: String)

data class Data(val translations: List<Translation>)

data class CallResponse(val data: Data)

data class ErrorResponse(val message: String)
