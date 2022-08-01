package com.hxl.arithmathics.presentation.fragment.game

import android.annotation.SuppressLint
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
import com.hxl.data.model.DifficultyEnums
import com.hxl.domain.models.Difficulty
import com.hxl.domain.models.Question
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

@AndroidEntryPoint
class GameFragment : Fragment() {
    private val vm: GameFragmentViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding

    private var time = 0
    private lateinit var difEnum: Difficulty
    private val disposable = CompositeDisposable()
    private lateinit var timerTask: TimerTask
    private lateinit var questionArray: Array<Question>
    private lateinit var answerArray: Array<String>
    private lateinit var gamePage: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamePage = binding.gamePager
        binding.btnAnswer.style(R.style.Default_Button)
        disposable.add(
            vm.readDifficulty()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { diff ->
                    difEnum = when (vm.getMode()) {
                        0 -> DifficultyEnums.EASY.difficulty
                        1 -> DifficultyEnums.MEDIUM.difficulty
                        2 -> DifficultyEnums.HARD.difficulty
                        else -> diff
                    }
                    answerArray = Array(difEnum.levels) { "" }
                    questionArray = Array(difEnum.levels) {
                        vm.getQuestion(difEnum.operations, difEnum.numberRange, difEnum.operators)
                    }
                    gamePage.adapter = GamePagerAdapter(this,
                        difEnum.levels,
                        Array(difEnum.levels) { questionArray[it].question })
                    startTimer()
                    time = 0
                    gamePage.registerOnPageChangeCallback(
                        object : ViewPager2.OnPageChangeCallback() {
                            @SuppressLint("SetTextI18n")
                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {
                                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                                binding.tiAnswer.setText(answerArray[position])
                                binding.tvPosition.text =
                                    "${gamePage.currentItem + 1}/${difEnum.levels}"

                                if (gamePage.currentItem == answerArray.size - 1) {
                                    binding.btnAnswer.text = resources.getString(R.string.finish)
                                    binding.btnAnswer.style(R.style.Finish_Button)
                                } else {
                                    binding.btnAnswer.text = resources.getString(R.string.continue_)
                                }
                            }
                        }
                    )
                }
        )
        binding.tiAnswer.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                nextQuestion()
                true
            } else { false }
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
                binding.tvTimer.text = vm.getTimerText(difEnum.time - time)
                if (time == difEnum.time) { endGame() }
            }
        } else { functions.add { binding.tvTimer.text = vm.getTimerText(time) } }

        timerTask = object : TimerTask() {
            override fun run() { requireActivity().runOnUiThread { for (i in functions) { i() } } }
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
        disposable.clear()
        (requireActivity() as MainActivity).replaceFragment(ResultsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        timerTask.cancel()
    }
}