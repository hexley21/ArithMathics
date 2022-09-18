package com.hxl.arithmathics.presentation.fragment.game

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.KeyboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Keyboard(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs, 0), View.OnClickListener {
    private var binding: KeyboardBinding

    private var buttonBackSpace: Button
    private var buttonAnswer: Button
    private var buttonOne: Button
    private var buttonTwo: Button
    private var buttonThree: Button
    private var buttonFour: Button
    private var buttonFive: Button
    private var buttonSix: Button
    private var buttonSeven: Button
    private var buttonEight: Button
    private var buttonNine: Button
    private var buttonZero: Button
    private var buttonDot: Button
    private var buttonNegative: Button

    private val keyValues = SparseArray<String>()
    private lateinit var inputConnection: InputConnection
    private lateinit var answer: () ->Unit

    init {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = KeyboardBinding.inflate(layoutInflater, this, true)
        buttonBackSpace = binding.btnBackspace
        buttonAnswer = binding.btnAnswer
        buttonOne = binding.btnOne
        buttonTwo = binding.btnTwo
        buttonThree = binding.btnThree
        buttonFour = binding.btnFour
        buttonFive = binding.btnFive
        buttonSix = binding.btnSix
        buttonSeven = binding.btnSeven
        buttonEight = binding.btnEight
        buttonNine = binding.btnNine
        buttonZero = binding.btnZero
        buttonDot = binding.btnDot
        buttonNegative = binding.btnNegative

        buttonBackSpace.setOnClickListener(this)
        buttonAnswer.setOnClickListener(this)
        buttonOne.setOnClickListener(this)
        buttonTwo.setOnClickListener(this)
        buttonThree.setOnClickListener(this)
        buttonFour.setOnClickListener(this)
        buttonFive.setOnClickListener(this)
        buttonSix.setOnClickListener(this)
        buttonSeven.setOnClickListener(this)
        buttonEight.setOnClickListener(this)
        buttonNine.setOnClickListener(this)
        buttonZero.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonNegative.setOnClickListener(this)

        keyValues.put(buttonOne.id, getStringResource(R.string.btn_one))
        keyValues.put(buttonTwo.id, getStringResource(R.string.btn_two))
        keyValues.put(buttonThree.id, getStringResource(R.string.btn_three))
        keyValues.put(buttonFour.id, getStringResource(R.string.btn_four))
        keyValues.put(buttonFive.id, getStringResource(R.string.btn_five))
        keyValues.put(buttonSix.id, getStringResource(R.string.btn_six))
        keyValues.put(buttonSeven.id, getStringResource(R.string.btn_seven))
        keyValues.put(buttonEight.id, getStringResource(R.string.btn_eight))
        keyValues.put(buttonNine.id, getStringResource(R.string.btn_nine))
        keyValues.put(buttonZero.id, getStringResource(R.string.btn_zero))
        keyValues.put(buttonNegative.id, getStringResource(R.string.btn_negative))
        keyValues.put(buttonDot.id, getStringResource(R.string.btn_dot))

    }

    override fun onClick(v: View) {
        when (v.id) {
            buttonAnswer.id -> answer()
            buttonBackSpace.id -> onBackspace()
            else -> onElse(v.id)
        }
    }

    private fun onBackspace() {
        if (TextUtils.isEmpty(inputConnection.getSelectedText(0))) {
            inputConnection.deleteSurroundingText(1, 0)
        }
        else {
            commitText("", 1)
        }
    }

    private fun onElse(id: Int) {
        commitText(keyValues[id])
    }

    private fun getStringResource(id: Int): String {
        return context.resources.getString(id)
    }

    private fun commitText(text: String, position: Int = 1) {
        inputConnection.commitText(text, position)
    }

    fun setConnection(ic: InputConnection?, unit: () -> Unit) {
        inputConnection = ic!!
        answer = unit
    }

}
