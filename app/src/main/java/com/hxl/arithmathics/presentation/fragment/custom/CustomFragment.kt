package com.hxl.arithmathics.presentation.fragment.custom

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmathics.databinding.FragmentCustomBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.game.GameFragment
import com.hxl.arithmathics.presentation.fragment.game_history.GameResultFormatter
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.domain.models.Difficulty
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.roundToInt

@AndroidEntryPoint
class CustomFragment : Fragment() {
    private val vm: CustomFragmentViewModel by viewModels()
    private lateinit var binding: FragmentCustomBinding
    private val disposable = CompositeDisposable()
    private var operators = ""
    private var percentageList = emptyList<Double>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val levelsSlider = binding.slLevels
        val operationsSlider = binding.slOperations
        val rangeSlider = binding.slRange
        val timerSlider = binding.slTimer

        binding.timer = vm.getTimer()
        levelsSlider.addOnChangeListener { _, value, _ -> binding.levels = value.toInt() }
        operationsSlider.addOnChangeListener { _, value, _ -> binding.operations = value.toInt() }
        rangeSlider.addOnChangeListener { slider, _, _ ->
            binding.range = slider.values[0].toInt()..slider.values[1].toInt()
        }
        timerSlider.addOnChangeListener { slider, _, _ ->
            binding.time = GameResultFormatter.getTimerText(slider.value.toInt())
        }
        timerSlider.setLabelFormatter { value: Float -> GameResultFormatter.getTimerText(value.toInt()) }

        disposable.add(vm.readDifficulty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ diff ->
                binding.difficulty = diff
                binding.levels = diff.levels
                binding.operations = diff.operations
                binding.time = GameResultFormatter.getTimerText(diff.time)
                rangeSlider.setValues(
                    diff.numberRange.first.toFloat(),
                    diff.numberRange.last.toFloat()
                )
                operators = diff.operators
                calculatePercentage()
                disposable.clear()
            },
                {
                    Log.e(LocalDatabase.TAG, "The record couldn't be read", it)
                }
            )
        )
        binding.btnPlusAdd.setOnClickListener { btnAdd('+') }
        binding.btnMinusAdd.setOnClickListener { btnMin('+') }

        binding.btnPlusSub.setOnClickListener { btnAdd('-') }
        binding.btnMinusSub.setOnClickListener { btnMin('-') }

        binding.btnPlusMult.setOnClickListener { btnAdd('*') }
        binding.btnMinusMult.setOnClickListener { btnMin('*') }

        binding.btnPlusDiv.setOnClickListener { btnAdd('/') }
        binding.btnMinusDiv.setOnClickListener { btnMin('/') }

        binding.btnStartCustom.setOnClickListener {

            val newDifficulty =
                Difficulty(
                    levelsSlider.value.toInt(),
                    operationsSlider.value.toInt(),
                    rangeSlider.values[0].toInt()..rangeSlider.values[1].toInt(),
                    operators,
                    timerSlider.value.toInt()
                )
            disposable.add(vm.insertDifficulty(newDifficulty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e(LocalDatabase.TAG, "$it record was inserted")
                        disposable.clear()
                    },
                    {
                        Log.e(LocalDatabase.TAG, "Couldn't insert new record", it)
                        disposable.clear()
                    }
                )
            )
            (requireActivity() as MainActivity).replaceFragment(GameFragment(), true)
        }
    }

    private fun btnAdd(operator: Char) {
        if (operators.count() < 100) {
            operators += operator
            calculatePercentage()
        }
    }

    private fun btnMin(operator: Char) {
        if (operator in operators && operators.count() > 1) {
            val charList = operators.toCharArray().toMutableList()
            charList.remove(operator)
            operators = String(charList.toCharArray())
            calculatePercentage()
        }
    }

    private fun calculatePercentage() {
        val counts = mutableListOf<Int>()
        for (i in "+-*/") { counts.add(operators.count { it == i }) }
        percentageList = List(counts.size) {
            (counts[it].toDouble() / operators.count().toDouble() * 1000.0).roundToInt() / 10.0
        }
        binding.addPercent = "${counts[0]} - ${percentageList[0]} %"
        binding.subPercent = "${counts[1]} - ${percentageList[1]} %"
        binding.mulPercent = "${counts[2]} - ${percentageList[2]} %"
        binding.divPercent = "${counts[3]} - ${percentageList[3]} %"
    }
}
