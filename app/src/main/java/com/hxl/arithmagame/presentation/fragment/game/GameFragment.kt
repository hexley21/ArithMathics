package com.hxl.arithmagame.presentation.fragment.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hxl.arithmagame.databinding.FragmentGameBinding
import com.hxl.arithmagame.presentation.fragment.question.QuestionFragment
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
        gamePage.adapter = ViewPagerAdapter(this, vm.quantity, questionStrings)
        binding.btnAnswer.setOnClickListener {
            answerArray[gamePage.currentItem] = binding.tiAnswer.text.toString()
            gamePage.setCurrentItem(gamePage.currentItem + 1, true)
        }
        gamePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.tvPosition.text = "${gamePage.currentItem + 1}/${vm.quantity}"
                binding.tiAnswer.setText(answerArray[position])
            }
        })
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