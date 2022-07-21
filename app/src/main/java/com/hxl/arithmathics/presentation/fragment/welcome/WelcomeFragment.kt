package com.hxl.arithmathics.presentation.fragment.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hxl.arithmathics.databinding.FragmentWelcomeBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.menu.MenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private val vm: WelcomeFragmentViewModel by viewModels()
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)

        when(vm.language){
            "ka" -> binding.rbKa.isChecked = true
            else -> binding.rbEn.isChecked = true
        }

        binding.languageGroup.setOnCheckedChangeListener { _, i ->
            when(i){
                binding.rbKa.id -> vm.language = "ka"
                binding.rbEn.id -> vm.language = "en"
            }
            (requireActivity() as MainActivity).restartActivity()
        }

        binding.btnContinue.setOnClickListener {
            vm.saveWelcome(false)
            (requireActivity() as MainActivity).replaceFragment(MenuFragment())
        }
        return binding.root
    }

}