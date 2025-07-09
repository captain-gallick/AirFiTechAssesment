package com.nirvana.airfitechassesment.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nirvana.airfitechassesment.data.models.Airline
import java.sql.Types
import javax.inject.Inject

class JsonUtils @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    fun loadAirlinesFromAssets(): List<Airline> {
        return try {
            val json = context.assets.open("airlines.json")
                .bufferedReader()
                .use { it.readText() }

            val type = object : TypeToken<List<Airline>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

