package com.hxl.arithmagame.presentation.fragment.game_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hxl.arithmagame.databinding.FragmentGameHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameHistoryFragment : Fragment() {

    companion object{
        const val TAG: String = "game_history"
    }

    private val vm: GameHistoryFragmentViewModel by viewModels()
    lateinit var binding: FragmentGameHistoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvHistory = binding.rvHistory
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = GameHistoryRecyclerAdapter(vm.getGameHistory())
        rvHistory.scrollToPosition(vm.getGameHistory().size - 1)

        binding.topHistoryBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}