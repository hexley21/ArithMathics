package com.hxl.arithmathics.di

import com.hxl.domain.repository.DifficultyRepository
import com.hxl.domain.repository.GameHistoryRepository
import com.hxl.domain.repository.PreferenceRepository
import com.hxl.domain.repository.QuestionRepository
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import com.hxl.domain.usecase.database.game_history.GetGameHistory
import com.hxl.domain.usecase.database.game_history.SaveGameHistory
import com.hxl.domain.usecase.prefs.*
import com.hxl.domain.usecase.questions.GetQuestion
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

    @Provides
    fun provideGetTimer(preferenceRepository: PreferenceRepository): GetTimer {
        return GetTimer(preferenceRepository)
    }

    @Provides
    fun provideSaveTimer(preferenceRepository: PreferenceRepository): SaveTimer {
        return SaveTimer(preferenceRepository)
    }
    // endregion

    // region questions
    @Provides
    fun provideQuestion(questionRepository: QuestionRepository): GetQuestion {
        return GetQuestion(questionRepository)
    }
    // endregion

    // region custom
    @Provides
    fun provideGetDifficulty(difficultyRepository: DifficultyRepository): ReadDifficulty {
        return ReadDifficulty(difficultyRepository)
    }

    @Provides
    fun provideSaveDifficulty(difficultyRepository: DifficultyRepository): InsertDifficulty {
        return InsertDifficulty(difficultyRepository)
    }
    // endregion

    // region game history
    @Provides
    fun provideGameHistory(gameHistoryRepository: GameHistoryRepository): GetGameHistory{
        return GetGameHistory(gameHistoryRepository)
    }

    @Provides
    fun provideSaveGameHistory(gameHistoryRepository: GameHistoryRepository): SaveGameHistory {
        return SaveGameHistory(gameHistoryRepository)
    }
    // endregion
}