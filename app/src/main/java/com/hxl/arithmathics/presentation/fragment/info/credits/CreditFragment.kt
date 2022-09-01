package com.hxl.arithmathics.presentation.fragment.info.credits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hxl.arithmathics.databinding.FragmentCreditsBinding

class CreditFragment: Fragment() {

    lateinit var binding: FragmentCreditsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditsBinding.inflate(inflater, container, false)

        binding.rvCredits.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCredits.adapter = CreditsAdapter()
        binding.rvCredits.addItemDecoration(
            DividerItemDecoration(requireContext() , DividerItemDecoration.VERTICAL)
        )
        return binding.root
    }
}