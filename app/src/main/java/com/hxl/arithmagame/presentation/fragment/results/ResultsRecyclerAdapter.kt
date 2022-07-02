package com.hxl.arithmagame.presentation.fragment.results

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.style
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.ItemResultBinding
import com.hxl.domain.models.Question


class ResultsRecyclerAdapter(
    private val questions: Array<Question>,
    private val answers: Array<String>,
    private val corrects: Array<Boolean>
) : RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder>() {
    class ViewHolder(internal val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.binding.root.context
        val question = questions[position]
        val answer =
            if (answers[position] == "") "∅" else answers[position]
        holder.binding.tvResQuestion.text = "${question.question} = ${question.answer}"
        holder.binding.tvResAnswer.text = "${context.resources.getString(R.string.answer)}: $answer"


        if (corrects[position]) {
            val typedValue = TypedValue()
            holder.binding.tvResult.text = context.resources.getString(R.string.correct)
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorTertiaryContainer, typedValue, true)
            holder.binding.cvResult.setCardBackgroundColor(typedValue.data)
            holder.binding.tvResult.style(R.style.Correct_card_text)
            holder.binding.tvResAnswer.style(R.style.Correct_card_text)
            holder.binding.tvResQuestion.style(R.style.Correct_card_text)
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

}