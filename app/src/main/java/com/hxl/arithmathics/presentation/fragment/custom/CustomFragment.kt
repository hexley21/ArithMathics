package com.hxl.arithmathics.presentation.fragment.custom

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmathics.R
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

@AndroidEntryPoint
class CustomFragment : Fragment() {
    private val vm: CustomFragmentViewModel by viewModels()
    private lateinit var binding: FragmentCustomBinding
    private val disposable = CompositeDisposable()

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

        if (vm.getTimer()) {
            binding.tvTimerAttention.visibility = View.GONE
        }

        val txtLevels = binding.tvLevels.text
        levelsSlider.addOnChangeListener { _, value, _ ->
            binding.tvLevels.text = "$txtLevels: ${value.toInt()}"
        }
        val txtOperations = binding.tvOperations.text
        operationsSlider.addOnChangeListener { _, value, _ ->
            binding.tvOperations.text = "$txtOperations: ${value.toInt()}"
        }
        val txtRange = binding.tvRange.text
        rangeSlider.addOnChangeListener { slider, _, _ ->
            binding.tvRange.text = "$txtRange: ${slider.values[0].toInt()} - ${slider.values[1].toInt()}"
        }
        val txtTimer = binding.tvSlTimer.text
        timerSlider.addOnChangeListener { slider, _, _ ->
            binding.tvSlTimer.text = "$txtTimer - ${GameResultFormatter.getTimerText(slider.value.toInt())}"
        }
        disposable.add(vm.readDifficulty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { diff ->
                levelsSlider.value = diff.levels.toFloat()
                operationsSlider.value = diff.operations.toFloat()
                timerSlider.value = diff.time.toFloat()

                rangeSlider.setValues(
                    diff.numberRange.first.toFloat(),
                    diff.numberRange.last.toFloat()
                )
                checkCheckboxes(diff.operators)
                disposable.clear()
            }
        )

        timerSlider.setLabelFormatter { value: Float ->
            GameResultFormatter.getTimerText(value.toInt())
        }
        binding.cbOp4.setOnCheckedChangeListener { _, isChecked ->
            val visibility = when (isChecked) {
                true -> View.VISIBLE
                else -> View.GONE
            }
            binding.tvDecimalAttention.visibility = visibility
        }

        binding.btnStartCustom.setOnClickListener {
            var operators = ""

            if (binding.cbOp1.isChecked) { operators += "+" }
            if (binding.cbOp2.isChecked) { operators += "-" }
            if (binding.cbOp3.isChecked) { operators += "*" }
            if (binding.cbOp4.isChecked) { operators += "/" }

            if (operators.isNotEmpty()) {
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
            } else {
                (requireActivity() as MainActivity).toast(resources.getString(R.string.attention_custom))
            }
        }

    }

    private fun checkCheckboxes(operators: String) {
        binding.cbOp1.isChecked = "+" in operators
        binding.cbOp2.isChecked = "-" in operators
        binding.cbOp3.isChecked = "*" in operators
        binding.cbOp4.isChecked = "/" in operators
    }
}