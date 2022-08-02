package com.hxl.domain.repository

import com.hxl.domain.models.Question

/**
 * Question Repository interface for generating question.
 */
interface QuestionRepository {

    fun generateQuestion(
        operations: Int,
        numberRange: IntRange,
        operators: String,
        positives: Boolean
    ): Question

}