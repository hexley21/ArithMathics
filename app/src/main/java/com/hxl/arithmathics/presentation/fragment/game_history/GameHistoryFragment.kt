package com.hxl.arithmathics.presentation.fragment.game_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hxl.arithmathics.databinding.FragmentGameHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class GameHistoryFragment : Fragment() {

    private val vm: GameHistoryFragmentViewModel by viewModels()
    lateinit var binding: FragmentGameHistoryBinding
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvHistory = binding.rvHistory
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        disposable.add(vm.readGameHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { history ->
                rvHistory.adapter = GameHistoryRecyclerAdapter(history)
                rvHistory.scrollToPosition(history.size - 1)
            }
        )

        binding.topHistoryBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}