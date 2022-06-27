package com.hxl.data.repository

import com.hxl.data.storage.PreferenceStorage
import com.hxl.domain.repository.PreferenceRepository

class PreferenceRepositoryImpl(private val prefStorage: PreferenceStorage) : PreferenceRepository{
    var theme: Int
        get() = getTheme()
        set(value) = saveTheme(value)

    var mode: Int
        get() = getMode()
        set(value) = saveMode(value)

    var language: String
        get() = getLanguage()
        set(value) = saveLanguage(value)

    override fun getTheme(default: Int): Int {
        return prefStorage.get("theme", default)
    }

    override fun saveTheme(value: Int) {
        prefStorage.save("theme", value)
    }

    override fun getMode(default: Int): Int {
        return prefStorage.get("mode", default)
    }

    override fun saveMode(value: Int) {
        prefStorage.save("mode", value)
    }

    override fun getLanguage(default: String): String {
        return prefStorage.get("language", default)
    }

    override fun saveLanguage(value: String) {
        prefStorage.save("language", value)
    }

}