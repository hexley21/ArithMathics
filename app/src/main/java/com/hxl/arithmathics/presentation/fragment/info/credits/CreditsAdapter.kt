package com.hxl.arithmathics.presentation.fragment.info.credits

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.ItemCreditBinding

class CreditsAdapter : RecyclerView.Adapter<CreditsAdapter.ViewHolder>() {
    class ViewHolder(internal val binding: ItemCreditBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                binding.root.context.startActivity(
                    Intent(Intent.ACTION_VIEW , Uri.parse(
                    binding.root.context.resources.getStringArray(R.array.credits_links)[adapterPosition]
                ))
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCreditBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val resources = holder.binding.root.context.resources
        val titleItem = resources.getStringArray(R.array.credits_title)[position]
        val descItem = resources.getStringArray(R.array.credits_desc)[position]
        holder.binding.tvCreditTitle.text = titleItem
        holder.binding.tvCreditDesc.text = descItem
    }

    override fun getItemCount(): Int {
        return 8
    }

}