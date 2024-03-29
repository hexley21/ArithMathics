package com.hxl.domain.usecase.questions

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository

/**
 * Question use-case that provides generated question.
 */
class GetQuestion(private val questionRepository: QuestionRepository) {
    operator fun invoke(
        operations: Int,
        range: IntRange,
        operators: String,
        positives: Boolean
    ): Question {
        return questionRepository.generateQuestion(operations, range, operators, positives)
    }
}