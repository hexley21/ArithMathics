package com.hxl.arithmagame.presentation.fragment.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.paris.extensions.style
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.FragmentGameBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.results.ResultFragmentViewModel
import com.hxl.arithmagame.presentation.fragment.results.ResultsFragment
import com.hxl.domain.models.Question
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GameFragment : Fragment() {
    private var time = 0
    private lateinit var timerTask: TimerTask
    private lateinit var questionArray: Array<Question>
    private lateinit var answerArray: Array<String>

    private val vm: GameFragmentViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        questionArray = vm.generateQuestions()
        answerArray = Array(vm.levels) { "" }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gamePage: ViewPager2 = binding.gamePager
        val questionStrings = Array(vm.levels) { questionArray[it].question }
        binding.btnAnswer.style(R.style.Default_Button)
        gamePage.adapter = GamePagerAdapter(this, vm.levels, questionStrings)

        startTimer()
        time = 0

        gamePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.tvPosition.text = "${gamePage.currentItem + 1}/${vm.levels}"
                binding.tiAnswer.setText(answerArray[position])
                if (gamePage.currentItem == answerArray.size - 1) {
                    binding.btnAnswer.text = resources.getString(R.string.finish)
                    binding.btnAnswer.style(R.style.Finish_Button)
                } else {
                    binding.btnAnswer.text = resources.getString(R.string.continue_)
                }
            }
        })

        binding.btnAnswer.setOnClickListener {
            answerArray[gamePage.currentItem] = binding.tiAnswer.text.toString()
            if (gamePage.currentItem == answerArray.size - 1) {
                endGame()
            }
            gamePage.setCurrentItem(gamePage.currentItem + 1, true)
        }

        binding.btnEnd.setOnClickListener { endGame() }
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
                    for (i in functions) {
                        i()
                    }
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
        (requireActivity() as MainActivity).replaceFragment(ResultsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        timerTask.cancel()
    }
}