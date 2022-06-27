package com.hxl.data.storage.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.hxl.data.storage.PreferenceStorage

class SharedPreferenceStorage(context: Context): PreferenceStorage {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("preferences" , Context.MODE_PRIVATE)

    override fun save(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun save(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun get(key: String, default: Boolean): Boolean{
        return sharedPreferences.getBoolean(key, default)
    }

    override fun get(key: String, default: Int): Int{
        return sharedPreferences.getInt(key, default)
    }

    override fun get(key: String, default: String): String{
        return sharedPreferences.getString(key, default) ?: ""
    }
}