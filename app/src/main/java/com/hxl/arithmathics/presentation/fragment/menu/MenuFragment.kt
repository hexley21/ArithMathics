package com.hxl.arithmathics.presentation.fragment.menu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.FragmentMenuBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.custom.CustomFragment
import com.hxl.arithmathics.presentation.fragment.dialogs.language.LanguageDialog
import com.hxl.arithmathics.presentation.fragment.dialogs.theme.ThemeDialog
import com.hxl.arithmathics.presentation.fragment.game.GameFragment
import com.hxl.arithmathics.presentation.fragment.game_history.GameHistoryFragment
import com.hxl.arithmathics.presentation.fragment.info.InfoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private val vm: MenuFragmentViewModel by viewModels()
    private lateinit var binding: FragmentMenuBinding
    private val themeDialog = ThemeDialog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.theme -> {
                    (requireActivity() as MainActivity).showDialog(themeDialog, "theme_dialog")
                    true
                }
                R.id.language -> {
                    (requireActivity() as MainActivity).showDialog(
                        LanguageDialog(),
                        "language_dialog"
                    )
                    true
                }
                R.id.rate -> {
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=${requireActivity().packageName}")
                            )
                        )
                    } catch (e: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=${requireActivity().packageName}")
                            )
                        )
                    }
                    true
                }
                R.id.info -> {
                    (requireActivity() as MainActivity).replaceFragment(InfoFragment(), true)
                    true
                }
                else -> false
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(GameHistoryFragment(), true)
        }

        when (vm.mode) {
            1 -> binding.rbMedium.isChecked = true
            2 -> binding.rbHard.isChecked = true
            3 -> binding.rbCustom.isChecked = true
            else -> binding.rbEasy.isChecked = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                binding.rbMedium.id -> vm.mode = 1
                binding.rbHard.id -> vm.mode = 2
                binding.rbCustom.id -> vm.mode = 3
                else -> vm.mode = 0
            }
        }

        binding.btnStart.setOnClickListener {
            if (vm.mode != 3) {
                (requireActivity() as MainActivity).replaceFragment(GameFragment(), true)
            } else {
                (requireActivity() as MainActivity).replaceFragment(CustomFragment(), true)
            }
        }

        createSpinner(R.array.timer_array, binding.spTimerMode, vm.timer)
        binding.spTimerMode.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    vm.timer = when (p2) {
                        1 -> true
                        else -> false
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        createSpinner(R.array.positive_array, binding.spPositiveMode, vm.positive)
        binding.spPositiveMode.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    vm.positive = when (p2) {
                        1 -> true
                        else -> false
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun createSpinner(array: Int, spinner: Spinner, state: Boolean) {
        ArrayAdapter.createFromResource(
            requireContext(), array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        when (state) {
            true -> spinner.setSelection(1, false)
            else -> spinner.setSelection(0, false)
        }
    }

    override fun onStop() {
        super.onStop()
        if (themeDialog.isStateSaved || themeDialog.isAdded || themeDialog.isVisible) {
            themeDialog.dismissAllowingStateLoss()
        }
    }
}