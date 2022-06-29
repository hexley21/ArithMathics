package com.hxl.domain.usecase.questions

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository

class GetQuestion(private val questionRepository: QuestionRepository) {

    operator fun invoke(
        operations: Int,
        range: Int,
        operators: Array<String> = arrayOf("+", "-", "*", "/")
    ): Question {
        return questionRepository.generateQuestion(operations, range, operators)
    }

    fun easy(): Question {
        return questionRepository.generateQuestion(3, 10, arrayOf("+", "-"))
    }

    fun medium(): Question {
        return questionRepository.generateQuestion(5, 30, arrayOf("+", "-", "*"))
    }

    fun hard(): Question {
        return questionRepository.generateQuestion(5, 100, arrayOf("+", "-", "*", "/"))
    }
}