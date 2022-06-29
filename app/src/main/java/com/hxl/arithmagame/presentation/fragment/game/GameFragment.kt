package com.hxl.arithmagame.presentation.fragment.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmagame.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {
    companion object {
        const val TAG: String = "game_fragment"
    }

    private val vm: GameFragmentViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)

        generateQuestion()
        binding.btnGenerate.setOnClickListener { generateQuestion() }

        return binding.root
    }

    private fun generateQuestion() {
        val question = when (vm.getMode()) {
            0 -> vm.getQuestion.easy()
            1 -> vm.getQuestion.medium()
            2 -> vm.getQuestion.hard()
            else -> vm.getQuestion(10, 1000)
        }
        binding.tvQuestion.text = question.question
        binding.tvAnswer.text = question.answer
    }
}