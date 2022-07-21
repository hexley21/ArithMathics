package com.hxl.arithmathics.presentation.fragment.dialogs.language

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.hxl.arithmathics.R
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.dialogs.PopDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageDialog : PopDialog() {
    private val vm: LanguageViewModel by viewModels()
    private lateinit var config: String
    private val modes = arrayOf("en", "ka")
    override var checkedItem: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        title = resources.getString(R.string.select_language)
        choice = resources.getStringArray(R.array.language_choice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = vm.language
        checkedItem = modes.indexOf(config)
    }

    // Saves and applies new Language to the app by restarting it
    override fun positiveListener(checkedItem: Int) {
        if (checkedItem != modes.indexOf(config)) {
            vm.language = modes[checkedItem]
            (requireActivity() as MainActivity).restartActivity()
        }
    }
}