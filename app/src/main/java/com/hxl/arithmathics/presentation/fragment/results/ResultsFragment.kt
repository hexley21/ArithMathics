package com.hxl.arithmathics.presentation.fragment.results

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hxl.arithmathics.databinding.FragmentResultBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.game_history.GameResultFormatter
import com.hxl.arithmathics.presentation.fragment.menu.MenuFragment
import com.hxl.data.model.DifficultyEnums
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private val vm: ResultFragmentViewModel by activityViewModels()
    private lateinit var binding: FragmentResultBinding
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topResultsBar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(MenuFragment())
        }
        val rvResults = binding.rvResults
        rvResults.layoutManager = LinearLayoutManager(requireContext())
        disposable.add(vm.readDifficulty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { diff ->
                val gameMode = when (vm.getMode()) {
                    0 -> DifficultyEnums.EASY.difficulty
                    1 -> DifficultyEnums.MEDIUM.difficulty
                    2 -> DifficultyEnums.HARD.difficulty
                    else -> diff
                }
                rvResults.adapter = ResultsRecyclerAdapter(vm.questions, vm.answers, vm.compareAnswers(gameMode))
                binding.corrects = vm.corrects
                binding.questions = vm.answers.size
                binding.timeSpent = GameResultFormatter.getTimerText(vm.time)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}