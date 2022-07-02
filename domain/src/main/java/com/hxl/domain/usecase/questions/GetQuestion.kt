package com.hxl.domain.usecase.questions

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository

class GetQuestion(private val questionRepository: QuestionRepository) {

    companion object{
        const val easy = 5
        const val medium = 7
        const val hard = 10
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