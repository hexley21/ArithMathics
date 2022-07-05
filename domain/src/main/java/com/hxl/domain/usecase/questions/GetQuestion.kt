package com.hxl.domain.usecase.questions

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository

class GetQuestion(private val questionRepository: QuestionRepository) {

    companion object{
        const val easyLevels = 5
        const val easyRange = 10
        const val easyOperations = 3
        const val easyOperators = 2
        const val easyTime = 120

        const val mediumLevels = 5
        const val mediumRange = 30
        const val mediumOperations = 5
        const val mediumOperators = 3
        const val mediumTime = 300

        const val hardLevels = 5
        const val hardRange = 100
        const val hardOperations = 5
        const val hardOperators = 4
        const val hardTime = 600
    }
    operator fun invoke(
        operations: Int,
        range: IntRange,
        operators: Array<String> = arrayOf("+", "-", "*", "/")
    ): Question {
        return questionRepository.generateQuestion(operations, range, operators)
    }

    fun easy(): Question {
        return questionRepository.generateQuestion(3, 1..10, arrayOf("+", "-"))
    }

    fun medium(): Question {
        return questionRepository.generateQuestion(5, 1..30, arrayOf("+", "-", "*"))
    }

    fun hard(): Question {
        return questionRepository.generateQuestion(5, 1..100, arrayOf("+", "-", "*", "/"))
    }
}