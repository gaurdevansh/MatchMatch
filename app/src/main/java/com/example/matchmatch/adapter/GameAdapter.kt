package com.example.matchmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmatch.databinding.CardItemBinding

class GameAdapter(): RecyclerView.Adapter<GameAdapter.GameViewholder>() {

    private var cardsList: MutableList<Int> = mutableListOf()

    inner class GameViewholder(private val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(imageId: Int) {
            binding.ivCard.setImageResource(imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewholder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflator, parent, false)
        return GameViewholder(binding)
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: GameViewholder, position: Int) {
        holder.bind(cardsList[position])
    }

    public fun updateList(cardList: List<Int>) {
        this.cardsList = cardList.toMutableList()
        notifyDataSetChanged()
    }
}