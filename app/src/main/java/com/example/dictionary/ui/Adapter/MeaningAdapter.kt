package com.example.dictionary.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.data.model.Meaning
import com.example.dictionary.databinding.ItemMeaningBinding

class MeaningAdapter(private var meaningList: List<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {
    class MeaningViewHolder(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: Meaning) {
            binding.tvPartOfSpeech.text = meaning.partOfSpeech

            binding.tvDefinition.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex + 1).toString() + ". " + it.definition.toString()
            }

            if (meaning.synonyms.isEmpty()) {
                binding.tvSynonymsTitle.visibility = View.GONE
                binding.tvSynonyms.visibility = View.GONE
            } else {
                binding.tvSynonymsTitle.visibility = View.VISIBLE
                binding.tvSynonyms.visibility = View.VISIBLE
                binding.tvSynonyms.text = meaning.synonyms.joinToString(", ")
            }

            if (meaning.antonyms.isEmpty()) {
                binding.tvAntonymsTitle.visibility = View.GONE
                binding.tvAntonyms.visibility = View.GONE
            } else {
                binding.tvAntonymsTitle.visibility = View.VISIBLE
                binding.tvAntonyms.visibility = View.VISIBLE
                binding.tvAntonyms.text = meaning.antonyms.joinToString(", ")
            }
        }
    }

    fun updateNewData(newMeaningList: List<Meaning>) {
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = ItemMeaningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}