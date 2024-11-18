package com.example.challengekaizengaming.manager

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class ServiceAdapter : TypeAdapter<Any>() {
    override fun write(out: JsonWriter?, value: Any?) {
    }

    override fun read(reader: JsonReader?): Any {
        val jsonElement = JsonParser.parseReader(reader)

        return if (jsonElement is JsonObject && jsonElement.has("d") && jsonElement["d"] is JsonObject) {
            Gson().fromJson(jsonElement["d"], List::class.java)
        } // Quando 'd' for uma String
        else if(jsonElement is JsonObject && jsonElement.has("d") && jsonElement["d"] is JsonPrimitive &&
            jsonElement["d"].asString != null ){
            Gson().fromJson(jsonElement["d"], String::class.java)
        } else {

        }
    }
}