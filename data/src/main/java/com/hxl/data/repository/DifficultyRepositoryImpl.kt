package com.hxl.data.repository

import com.hxl.data.model.Json
import com.hxl.data.storage.InternalStorage
import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository

class DifficultyRepositoryImpl(private val internalStorage: InternalStorage) :
    DifficultyRepository {
    override fun readCustom(): Difficulty {
        return Json.toCustom(internalStorage.read("custom"))
    }

    override fun insertCustom(difficulty: Difficulty) {
        internalStorage.write(Json.toJson(difficulty), "custom")
    }
}