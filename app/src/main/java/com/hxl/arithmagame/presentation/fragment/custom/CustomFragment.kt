package com.hxl.arithmagame.presentation.fragment.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.FragmentCustomBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.game.GameFragment
import com.hxl.arithmagame.presentation.fragment.game_history.GameResultFormatter
import com.hxl.domain.models.Custom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomFragment : Fragment() {
    companion object {
        const val TAG = "custom_fragment"
    }

    private val vm: CustomFragmentViewModel by viewModels()
    private lateinit var binding: FragmentCustomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val custom = vm.custom

        val levelsSlider = binding.slLevels
        val operationsSlider = binding.slOperations
        val rangeSlider = binding.slRange
        val timerSlider = binding.slTimer

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
            binding.tvRange.text =
                "$txtRange: ${slider.values[0].toInt()} - ${slider.values[1].toInt()}"
        }

        val txtTimer = binding.tvSlTimer.text
        timerSlider.addOnChangeListener { slider, _, _ ->
            binding.tvSlTimer.text =
                "$txtTimer - ${GameResultFormatter.getTimerText(slider.value.toInt())}"
        }

        levelsSlider.value = custom.levels.toFloat()
        operationsSlider.value = custom.operations.toFloat()
        timerSlider.setLabelFormatter { value: Float ->
            GameResultFormatter.getTimerText(value.toInt())
        }

        val start = custom.numberRange.first.toFloat()
        val end = custom.numberRange.last.toFloat()
        rangeSlider.setValues(start, end)


        binding.cbOp1.isChecked = "+" in custom.operators
        binding.cbOp2.isChecked = "-" in custom.operators
        binding.cbOp3.isChecked = "*" in custom.operators
        binding.cbOp4.isChecked = "/" in custom.operators

        binding.btnStartCustom.setOnClickListener {
            val levels = levelsSlider.value.toInt()
            val operations = operationsSlider.value.toInt()
            val rangeFrom = rangeSlider.values[0].toInt()
            val rangeTo = rangeSlider.values[1].toInt()
            val operators: MutableList<String> = mutableListOf()
            if (binding.cbOp1.isChecked) {
                operators.add("+")
            }
            if (binding.cbOp2.isChecked) {
                operators.add("-")
            }
            if (binding.cbOp3.isChecked) {
                operators.add("*")
            }
            if (binding.cbOp4.isChecked) {
                operators.add("/")
            }
            if (operators.isNotEmpty()) {
                vm.custom = Custom(levels, operations, rangeFrom..rangeTo, operators.toTypedArray())

                (requireActivity() as MainActivity).replaceFragment(GameFragment(), true)
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.attention_custom),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}