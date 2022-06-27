package com.hxl.arithmagame.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.FragmentMenuBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.dialogs.theme.ThemeDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {
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
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        if (themeDialog.isStateSaved || themeDialog.isAdded || themeDialog.isVisible){
            themeDialog.dismissAllowingStateLoss()
        }
    }
}