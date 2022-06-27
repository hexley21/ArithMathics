package com.hxl.arithmagame.di

import com.hxl.domain.repository.PreferenceRepository
import com.hxl.domain.usecase.prefs.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule