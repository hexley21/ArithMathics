package com.hxl.data.repository

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.roundToInt

class QuestionRepositoryImpl : QuestionRepository {

    private val negativeRange = -1000000.0..1000000.0
    private val positiveRange = 0.0..1000000.0

    override fun generateQuestion(operations: Int, numberRange: IntRange, operators: String, positives: Boolean): Question {
        var question = ""
        var expression: Double
        val answerRange = when (positives){
            true -> positiveRange
            else -> negativeRange
        }
        while (true){
            for (i in 0..operations){
                question += "${numberRange.random()}"
                if (i < operations){
                    question += " ${operators[(operators.indices).random()]} "
                }
            }

            expression = ExpressionBuilder(question).build().evaluate()
            if (expression in answerRange){
                break
            }
            question = ""
        }

        var answer = ((expression * 10.0).roundToInt() / 10.0).toString()
        if (answer.endsWith(".0")) {
            answer = answer.dropLast(2)
        }
        return Question(question, answer)
    }

}