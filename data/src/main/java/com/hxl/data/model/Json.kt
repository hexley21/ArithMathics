package com.hxl.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hxl.domain.models.Difficulty
import com.hxl.domain.models.GameResult
import java.util.*

class Json {
    companion object {
        fun toCustom(string: String?): Difficulty {
            val type = object : TypeToken<Difficulty>() {}.type
            return Gson().fromJson(string, type)
        }

        fun toGameHistory(string: String?): Stack<GameResult> {
            val type = object : TypeToken<Stack<GameResult>>() {}.type
            return Gson().fromJson(string, type)
        }

        fun <T> toJson(obj: T): String {
            return Gson().toJson(obj)
        }
    }
}