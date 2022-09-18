package com.hxl.arithmathics.presentation.fragment.results

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.domain.models.Difficulty
import com.hxl.domain.models.GameResult
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.database.game_history.InsertGameHistory
import com.hxl.domain.usecase.prefs.GetMode
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.roundToInt

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val insertGameHistory: InsertGameHistory,
    val getMode: GetMode,
    val readDifficulty: ReadDifficulty
) : ViewModel() {

    lateinit var questions: Array<Question>
    lateinit var answers: Array<String>
    private val disposable = CompositeDisposable()
    private val operators: HashMap<Char, Int> = hashMapOf('+' to 1, '-' to 2, '×' to 3, '÷' to 4)
    private val numbers = arrayOf('0', '1', '2' , '3' ,'4', '5', '6', '7', '8', '9')
    var corrects: Int = 0
    var time: Int = 0

    fun compareAnswers(difficulty: Difficulty): Array<Boolean> {
        val correctArray = Array(questions.size) { questions[it].answer == answers[it] }
        corrects = correctArray.count { it }
        saveGame(difficulty)
        return correctArray
    }

    private fun saveGame(gameMode: Difficulty) {
        val difficulty = calculateDifficulty(gameMode.operations)
        val newRecord = GameResult(difficulty, questions.size, corrects, time)
        disposable.add(insertGameHistory(newRecord)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.e(LocalDatabase.TAG, "$newRecord record was inserted") },
                { Log.e(LocalDatabase.TAG, "Couldn't insert $newRecord record", it) }
            )
        )
    }

    private fun calculateDifficulty(operations: Int): Int {
        var difficulty = 0.0
        for (i in questions) {
            difficulty += getDifficulty(i, operations)
        }
        return difficulty.roundToInt() / questions.size
    }

    private fun getDifficulty(question: Question, operations: Int): Double {
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

    private fun questionToNumbers(question: String, operations: Int): Array<Int> {
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

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
