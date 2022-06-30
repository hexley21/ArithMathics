package com.hxl.data.storage.internal

import android.content.Context
import com.hxl.data.storage.InternalStorage
import java.io.*

class FileStorage(private val context: Context) : InternalStorage {

    override fun write(string: String?, fileName: String) {
        if (string != null) {
            val fileWriter = FileWriter(File(context.filesDir, fileName))
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(string)
            bufferedWriter.close()
        } else throw NullPointerException()
    }

    override fun read(fileName: String): String {
        val bufferedReader = BufferedReader(FileReader(File(context.filesDir, fileName)))
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }
}