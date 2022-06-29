package com.hxl.data.repository

import com.hxl.domain.models.Question
import com.hxl.domain.repository.QuestionRepository
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.roundToInt

class QuestionRepositoryImpl : QuestionRepository {

    override fun generateQuestion(
        operations: Int,
        numberRange: Int,
        operators: Array<String>
    ): Question {
        var question = ""
        var expression: Double
        while (true){
            for (i in 1 until operations){
                question += "${(0..numberRange).random()}"
                if (i < operations-1){
                    question += " ${operators[(operators.indices).random()]} "
                }
            }

            expression = ExpressionBuilder(question).build().evaluate()
            if (expression > -1000000.0 && expression < 1000000.0){
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