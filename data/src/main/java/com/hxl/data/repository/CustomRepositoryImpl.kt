package com.hxl.data.repository

import com.hxl.data.model.Json
import com.hxl.data.storage.InternalStorage
import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.repository.CustomRepository

class CustomRepositoryImpl(private val internalStorage: InternalStorage) : CustomRepository {
    override fun getCustom(): QuestionDifficulty {
        return Json.toCustom(internalStorage.read("custom"))
    }

    override fun insertCustom(questionDifficulty: QuestionDifficulty) {
        internalStorage.write(Json.toJson(questionDifficulty), "custom")
    }
}