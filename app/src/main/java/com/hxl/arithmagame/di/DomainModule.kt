package com.hxl.arithmagame.di

import com.hxl.domain.repository.PreferenceRepository
import com.hxl.domain.usecase.prefs.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule{

    // region prefs
    @Provides
    fun provideGetTheme(preferenceRepository: PreferenceRepository): GetTheme {
        return GetTheme(preferenceRepository)
    }

    @Provides
    fun provideSaveTheme(preferenceRepository: PreferenceRepository): SaveTheme {
        return SaveTheme(preferenceRepository)
    }

    @Provides
    fun provideGetMode(preferenceRepository: PreferenceRepository): GetMode {
        return GetMode(preferenceRepository)
    }

    @Provides
    fun provideSaveMode(preferenceRepository: PreferenceRepository): SaveMode {
        return SaveMode(preferenceRepository)
    }

    @Provides
    fun provideGetLanguage(preferenceRepository: PreferenceRepository): GetLanguage {
        return GetLanguage(preferenceRepository)
    }

    @Provides
    fun provideSaveLanguage(preferenceRepository: PreferenceRepository): SaveLanguage {
        return SaveLanguage(preferenceRepository)
    }

    @Provides
    fun provideGetWelcome(preferenceRepository: PreferenceRepository): GetWelcome {
        return GetWelcome(preferenceRepository)
    }

    @Provides
    fun provideSaveWelcome(preferenceRepository: PreferenceRepository): SaveWelcome {
        return SaveWelcome(preferenceRepository)
    }
    // endregion

}