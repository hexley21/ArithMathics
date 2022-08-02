package com.hxl.arithmathics.presentation.fragment.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hxl.arithmathics.presentation.fragment.question.QuestionFragment

class GamePagerAdapter(
    fragment: Fragment,
    private val levels: Int,
    private val question: Array<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = levels

    override fun createFragment(position: Int): Fragment {
        val fragment = QuestionFragment()
        fragment.arguments = Bundle().apply { putString("question", question[position]) }
        return fragment
    }
}