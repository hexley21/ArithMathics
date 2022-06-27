package com.hxl.domain.repository

interface PreferenceRepository{

    fun getTheme(default: Int = -1): Int

    fun saveTheme(value: Int)

    fun getMode(default: Int = 0): Int

    fun saveMode(value: Int)

    fun getLanguage(default: String = "en"): String

    fun saveLanguage(value: String)

    fun getWelcome(default: Boolean = true): Boolean

    fun saveWelcome(value: Boolean)

}