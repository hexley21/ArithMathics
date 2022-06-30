package com.hxl.arithmagame.presentation.fragment.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxl.arithmagame.databinding.FragmentCustomBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.game.GameFragment

class CustomFragment: Fragment() {
    companion object{
        const val TAG = "custom_fragment"
    }

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
        binding.btnStartCustom.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(GameFragment(), GameFragment.TAG)
        }
    }
}