package com.hxl.data.storage

interface InternalStorage {

    fun read(fileName: String): String

    fun write(string: String?, fileName: String)
}