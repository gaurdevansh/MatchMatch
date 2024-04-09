package com.example.matchmatch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmatch.R
import com.example.matchmatch.databinding.CardItemBinding
import com.example.matchmatch.model.CardState
import com.example.matchmatch.utils.OnCardClickListener
import com.example.matchmatch.utils.State

class GameAdapter(val listener: OnCardClickListener): RecyclerView.Adapter<GameAdapter.GameViewholder>() {

    private var cardsList: MutableList<CardState> = mutableListOf()

    inner class GameViewholder(private val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardState) {
            if (card.state == State.FLIPPED) {
                binding.ivCard.setImageResource(card.image)
            } else if (card.isMatched) {
                binding.ivCard.setImageResource(card.image)
            } else {
                binding.ivCard.setImageResource(R.drawable.card_unflipped)
            }
            binding.ivCard.setOnClickListener {
                listener.onClick(adapterPosition)
            }
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

    @SuppressLint("NotifyDataSetChanged")
    public fun updateList(cardList: List<CardState>) {
        this.cardsList = cardList.toMutableList()
        notifyDataSetChanged()
    }
}