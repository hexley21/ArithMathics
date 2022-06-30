package com.hxl.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hxl.domain.models.Custom

class Json {
    companion object {
        fun toCustom(string: String?): Custom {
            val type = object : TypeToken<Custom>() {}.type
            return Gson().fromJson(string, type)
        }

        fun toJson(custom: Custom): String {
            return Gson().toJson(custom)
        }
    }
}