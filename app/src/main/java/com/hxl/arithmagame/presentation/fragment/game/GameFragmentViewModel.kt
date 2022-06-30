package com.hxl.arithmagame.presentation.fragment.game

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.questions.GetQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    private val getQuestion: GetQuestion,
    private val getMode: GetMode
) : ViewModel() {
    val quantity = when (getMode()) {
        1 -> GetQuestion.medium
        2 -> GetQuestion.hard
        else -> GetQuestion.easy
    }

    fun generateQuestions(): Array<Question> {
        return when(getMode()){
            1 -> mediumQuestions()
            2 -> hardQuestions()
            else -> easyQuestions()
        }
    }

    private fun easyQuestions(): Array<Question> {
        val questionList = mutableListOf<Question>()
        for (i in 0 until quantity) {
            questionList.add(getQuestion.easy())
        }
        return questionList.toTypedArray()
    }

    private fun mediumQuestions(): Array<Question> {
        val questionList = mutableListOf<Question>()
        for (i in 0 until quantity) {
            questionList.add(getQuestion.medium())
        }
        return questionList.toTypedArray()
    }

    private fun hardQuestions(): Array<Question> {
        val questionList = mutableListOf<Question>()
        for (i in 0 until quantity) {
            questionList.add(getQuestion.hard())
        }
        return questionList.toTypedArray()
    }
}