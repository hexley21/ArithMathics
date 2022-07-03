package com.hxl.arithmagame.presentation.fragment.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hxl.arithmagame.presentation.fragment.question.QuestionFragment

class GamePagerAdapter(
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