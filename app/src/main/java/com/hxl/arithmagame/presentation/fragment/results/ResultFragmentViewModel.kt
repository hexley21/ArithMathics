package com.hxl.arithmagame.presentation.fragment.results

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultFragmentViewModel @Inject constructor() : ViewModel(){
    lateinit var questions: Array<Question>
    lateinit var answers: Array<String>
    var corrects: Int = 0

    fun compareAnswers(): Array<Boolean>{
        val correctArray = Array(questions.size){ questions[it].answer == answers[it] }
        corrects = correctArray.count{ it }
        return correctArray
    }
}