package com.hxl.data.repository

import com.hxl.data.storage.PreferenceStorage
import com.hxl.domain.repository.PreferenceRepository

class PreferenceRepositoryImpl(private val prefStorage: PreferenceStorage) : PreferenceRepository