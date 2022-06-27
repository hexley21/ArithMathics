package com.hxl.arithmagame.presentation.fragment.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hxl.arithmagame.R

abstract class PopDialog : AppCompatDialogFragment() {
    protected open lateinit var title: String
    protected open lateinit var choice: Array<String>
    abstract var checkedItem: Int

    abstract fun positiveListener(checkedItem: Int)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setSingleChoiceItems(choice , checkedItem) { _ , i -> checkedItem = i }
            .setPositiveButton(getString(R.string.ok)) { _ , _ -> positiveListener(checkedItem) }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        return dialog.create()
    }
}