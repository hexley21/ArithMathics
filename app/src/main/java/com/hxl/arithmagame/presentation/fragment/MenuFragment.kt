package com.hxl.arithmagame.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.FragmentMenuBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.dialogs.theme.ThemeDialog
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
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.theme -> {
                    (requireActivity() as MainActivity).showDialog(themeDialog, "theme_dialog")
                    true
                }
                else -> false
            }
        }
        when(vm.mode){
            1 -> binding.rbMedium.isChecked = true
            2 -> binding.rbHard.isChecked = true
            3 -> binding.rbCustom.isChecked = true
            else -> binding.rbEasy.isChecked = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, i ->
            when(i){
                binding.rbMedium.id -> vm.mode = 1
                binding.rbHard.id -> vm.mode = 2
                binding.rbCustom.id -> vm.mode = 3
                else -> vm.mode = 0
            }
        }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        if (themeDialog.isStateSaved || themeDialog.isAdded || themeDialog.isVisible){
            themeDialog.dismissAllowingStateLoss()
        }
    }
}