package com.hxl.arithmathics.presentation.fragment.game_history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hxl.arithmathics.databinding.ItemHistoryBinding
import com.hxl.domain.models.GameResult

class GameHistoryRecyclerAdapter(private val gameHistory: List<GameResult>) :
    RecyclerView.Adapter<GameHistoryRecyclerAdapter.ViewHolder>() {
    class ViewHolder(internal val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameResult = gameHistory[position]
        val context = holder.binding.root.context

        holder.binding.difficulty =
            GameResultFormatter.getDifficultyText(gameResult.difficulty, context.resources)
        holder.binding.corrects = gameResult.corrects
        holder.binding.levels = gameResult.levels
        holder.binding.timeSpent = GameResultFormatter.getTimerText(gameResult.time)

        holder.binding.cvHistory.setCardBackgroundColor(
            GameResultFormatter.getDifficultyColor(
                gameResult.difficulty,
                context
            )
        )
    }

    override fun getItemCount(): Int {
        return gameHistory.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}