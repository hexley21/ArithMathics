package com.hxl.domain.models

import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionTest {

    @Test
    fun shouldReturnNumberArray() {
        val question = "10+20-30*40/50"
        val expected = arrayOf(10, 20, 30 ,40, 50)

        assertEquals(expected, Question.questionToNumbers(question, 4))
    }
    
    @Test
    fun shouldReturnDifficulty() {
        val question = Question("10+20-30*40/50", "6")

        assertEquals(64.1, Question.getDifficulty(question, 4), 0.1)
    }
}