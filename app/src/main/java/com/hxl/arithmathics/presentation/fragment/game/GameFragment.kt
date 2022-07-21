package com.hxl.arithmathics.presentation.fragment.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.paris.extensions.style
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.FragmentGameBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.results.ResultFragmentViewModel
import com.hxl.arithmathics.presentation.fragment.results.ResultsFragment
import com.hxl.domain.models.Question
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GameFragment : Fragment() {
    private val vm: GameFragmentViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding

    private var time = 0
    private lateinit var timerTask: TimerTask
    private lateinit var questionArray: Array<Question>
    private lateinit var answerArray: Array<String>
    private lateinit var gamePage: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        questionArray = vm.generateQuestions()
        answerArray = Array(vm.levels) { "" }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamePage = binding.gamePager
        binding.btnAnswer.style(R.style.Default_Button)
        gamePage.adapter =
            GamePagerAdapter(this, vm.levels, Array(vm.levels) { questionArray[it].question })

        startTimer()
        time = 0

        gamePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                binding.tiAnswer.setText(answerArray[position])
                binding.tvPosition.text = "${gamePage.currentItem + 1}/${vm.levels}"

                if (gamePage.currentItem == answerArray.size - 1) {
                    binding.btnAnswer.text = resources.getString(R.string.finish)
                    binding.btnAnswer.style(R.style.Finish_Button)
                } else {
                    binding.btnAnswer.text = resources.getString(R.string.continue_)
                }
            }
        })

        binding.tiAnswer.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                nextQuestion()
                true
            }
            else { false }
        }

        binding.btnAnswer.setOnClickListener { nextQuestion() }
        binding.btnEnd.setOnClickListener { endGame() }
    }

    private fun nextQuestion() {
        answerArray[gamePage.currentItem] = binding.tiAnswer.text.toString()
        if (gamePage.currentItem == answerArray.size - 1) {
            endGame()
        }
        gamePage.setCurrentItem(gamePage.currentItem + 1, true)
    }

    private fun startTimer() {
        val functions: MutableList<() -> Unit> = mutableListOf({ time++ })
        if (vm.getTimer()) {
            functions.add {
                binding.tvTimer.text = vm.getTimerText(vm.time - time)
                if (time == vm.time) {
                    endGame()
                }
            }
        } else {
            functions.add { binding.tvTimer.text = vm.getTimerText(time) }
        }

        timerTask = object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    for (i in functions) { i() }
                }
            }
        }
        Timer().scheduleAtFixedRate(timerTask, 0, 1000)
    }

    private fun endGame() {
        val resultVm: ResultFragmentViewModel by activityViewModels()
        resultVm.answers = answerArray
        resultVm.questions = questionArray
        resultVm.time = time
        if (binding.tiAnswer.text.toString() != "") {
            answerArray[gamePage.currentItem] = binding.tiAnswer.text.toString()
        }
        (requireActivity() as MainActivity).replaceFragment(ResultsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        timerTask.cancel()
    }
}