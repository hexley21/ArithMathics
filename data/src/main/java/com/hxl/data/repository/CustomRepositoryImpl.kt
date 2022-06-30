package com.hxl.data.repository

import com.hxl.data.model.Json
import com.hxl.data.storage.InternalStorage
import com.hxl.domain.models.Custom
import com.hxl.domain.repository.CustomRepository

class CustomRepositoryImpl(private val internalStorage: InternalStorage) : CustomRepository {
    override fun getCustom(): Custom {
        return Json.toCustom(internalStorage.read("custom"))
    }

    override fun saveCustom(custom: Custom) {
        internalStorage.write(Json.toJson(custom), "custom")
    }
}