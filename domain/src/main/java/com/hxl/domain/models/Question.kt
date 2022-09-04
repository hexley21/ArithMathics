package com.hxl.domain.models

import kotlin.math.log
import kotlin.math.pow

class Question(
    val question: String,
    val answer: String
) {
    companion object {

        private val operators: HashMap<Char, Int> = hashMapOf('+' to 1, '-' to 2, '×' to 3, '÷' to 4)
        private val numbers = arrayOf('0', '1', '2' , '3' ,'4', '5', '6', '7', '8', '9')

        fun getDifficulty(question: Question, operations: Int): Double {
            var answerDifficulty = log(question.answer.replace(',', '.').toDouble().pow(2.0), 2.0)
            if (answerDifficulty < 0) { answerDifficulty = 5.0 }
            else if (answerDifficulty == 0.0) { answerDifficulty = 3.0 }

            val operationsDifficulty = questionToNumbers(question.question, operations).sum() / operations
            var operatorDifficulty = 0

            val decimalDifficulty = if(question.answer.replace(',', '.').toDouble() % 1.0 != 0.0) { 0 } else { 20 }

            val operatorsCount: Array<Int> = Array(4) { 0 }
            for ((key, value) in operators) {
                operatorsCount[value-1] = question.question.count { it == key }
            }
            for (i in 0..3) {
                operatorDifficulty += operatorsCount[i] * (i/2 + 1)
            }
            return answerDifficulty + operationsDifficulty + operatorDifficulty + decimalDifficulty
        }

        fun questionToNumbers(question: String, operations: Int): Array<Int> {
            val numbersArray: Array<Int> = Array(operations + 1) { -1 }
            var number = ""
            var count = 0
            for (i in question.replace(" ", "")) {
                if (i in numbers) {
                    number += i
                }
                else {
                    numbersArray[count] = (number.toInt())
                    count++
                    number = ""
                }
            }
            numbersArray[count] = (number.toInt())
            return  numbersArray
        }
    }
}