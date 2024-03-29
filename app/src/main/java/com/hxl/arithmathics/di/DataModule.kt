package com.hxl.arithmathics.di

import android.content.Context
import androidx.room.Room
import com.hxl.data.repository.DifficultyRepositoryImpl
import com.hxl.data.repository.GameHistoryRepositoryImpl
import com.hxl.data.repository.PreferenceRepositoryImpl
import com.hxl.data.repository.QuestionRepositoryImpl
import com.hxl.data.storage.PreferenceStorage
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.data.storage.sharedprefs.SharedPreferenceStorage
import com.hxl.domain.repository.DifficultyRepository
import com.hxl.domain.repository.GameHistoryRepository
import com.hxl.domain.repository.PreferenceRepository
import com.hxl.domain.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(@ApplicationContext context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    @Provides
    @Singleton
    fun providePreferenceRepository(preferenceStorage: PreferenceStorage): PreferenceRepository {
        return PreferenceRepositoryImpl(preferenceStorage)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "arithmathics.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDifficultyRepository(database: LocalDatabase): DifficultyRepository {
        return DifficultyRepositoryImpl(database.difficultyDao())
    }

    @Provides
    @Singleton
    fun provideGameHistoryRepository(database: LocalDatabase): GameHistoryRepository {
        return GameHistoryRepositoryImpl(database.gameHistoryDao())
    }
}