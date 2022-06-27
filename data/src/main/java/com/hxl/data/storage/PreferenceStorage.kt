package com.hxl.data.storage

interface PreferenceStorage {

    fun save(key: String , value: Boolean)

    fun save(key: String , value: Int)

    fun save(key: String , value: String)

    fun get(key: String, default: Boolean): Boolean

    fun get(key: String, default: Int): Int

    fun get(key: String, default: String): String
}