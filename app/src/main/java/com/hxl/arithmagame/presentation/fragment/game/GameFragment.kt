package com.hxl.arithmagame.presentation.fragment.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.paris.extensions.style
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.FragmentGameBinding
import com.hxl.arithmagame.presentation.activity.MainActivity
import com.hxl.arithmagame.presentation.fragment.question.QuestionFragment
import com.hxl.arithmagame.presentation.fragment.results.ResultFragmentViewModel
import com.hxl.arithmagame.presentation.fragment.results.ResultsFragment
import com.hxl.domain.models.Question
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {
    companion object {
        const val TAG: String = "game_fragment"
    }

    private lateinit var questionArray: Array<Question>
    private lateinit var answerArray: Array<String>

    private val vm: GameFragmentViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        questionArray = vm.generateQuestions()
        answerArray = Array(vm.quantity) { "" }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gamePage: ViewPager2 = binding.gamePager
        val questionStrings = Array(vm.quantity) { questionArray[it].question }
        binding.btnAnswer.style(R.style.Default_Button)
        gamePage.adapter = ViewPagerAdapter(this, vm.quantity, questionStrings)

        gamePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.tvPosition.text = "${gamePage.currentItem + 1}/${vm.quantity}"
                binding.tiAnswer.setText(answerArray[position])
                if (gamePage.currentItem == answerArray.size-1) {
                    binding.btnAnswer.text = resources.getString(R.string.finish)
                    binding.btnAnswer.style(R.style.Finish_Button)
                } else {
                    binding.btnAnswer.text = resources.getString(R.string.continue_)
                }
            }
        })

        binding.btnAnswer.setOnClickListener {
            answerArray[gamePage.currentItem] = binding.tiAnswer.text.toString()
            if (gamePage.currentItem == answerArray.size-1) { endGame() }
            gamePage.setCurrentItem(gamePage.currentItem + 1, true)
        }

        binding.btnEnd.setOnClickListener { endGame() }
    }

    private fun endGame() {
        val resultVm: ResultFragmentViewModel by activityViewModels()
        resultVm.answers = answerArray
        resultVm.questions = questionArray
        (requireActivity() as MainActivity).replaceFragment(ResultsFragment())
    }
}

class ViewPagerAdapter(
    fragment: Fragment,
    private val quantity: Int,
    private val question: Array<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quantity

    override fun createFragment(position: Int): Fragment {
        val fragment = QuestionFragment()
        fragment.arguments = Bundle().apply {
            putString("question", question[position])
        }
        return fragment
    }
}