package com.hxl.domain.repository

import com.hxl.domain.models.Custom

interface CustomRepository {
    fun getCustom(): Custom

    fun saveCustom(custom: Custom)
}