package com.example.matchmatch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmatch.databinding.CardItemLargeBinding
import com.example.matchmatch.databinding.CardItemMediumBinding
import com.example.matchmatch.databinding.CardItemSmallBinding
import com.example.matchmatch.model.CardState
import com.example.matchmatch.utils.GameLevel
import com.example.matchmatch.utils.OnCardClickListener
import com.example.matchmatch.utils.State

class GameAdapter(val listener: OnCardClickListener,val gameLevel: GameLevel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cardsList: MutableList<CardState> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (gameLevel) {
            GameLevel.BEGINNER -> {
                val binding = CardItemLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LargeCardViewholder(binding)
            }
            GameLevel.INTERMEDIATE -> {
                val binding = CardItemLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LargeCardViewholder(binding)
            }
            GameLevel.ADVANCED -> {
                val binding = CardItemMediumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MediumCardViewholder(binding)
            }
            else -> {
                val binding = CardItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SmallCardViewholder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (gameLevel) {
            GameLevel.BEGINNER -> {
                (holder as LargeCardViewholder).bind(cardsList[position])
            }
            GameLevel.INTERMEDIATE -> {
                (holder as LargeCardViewholder).bind(cardsList[position])
            }
            GameLevel.ADVANCED -> {
                (holder as MediumCardViewholder).bind(cardsList[position])
            }
            GameLevel.EXPERT -> {
                (holder as SmallCardViewholder).bind(cardsList[position])
            }
        }

    }

    inner class SmallCardViewholder(private val binding: CardItemSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardState) {
            binding.ivFace.setImageResource(card.image)
            if (card.state == State.FLIPPED || card.isMatched) {
                binding.ivFace.visibility = View.VISIBLE
                binding.ivRear.visibility = View.GONE
            } else {
                binding.ivFace.visibility = View.GONE
                binding.ivRear.visibility = View.VISIBLE
            }
            var pos = -1
            if (adapterPosition != RecyclerView.NO_POSITION) {
                pos = adapterPosition
            }
            if (pos != -1) {
                binding.cardLayout.setOnClickListener {
                    binding.ivRear.visibility = View.GONE
                    binding.ivFace.visibility = View.VISIBLE
                    listener.resetOverlay()
                    android.os.Handler().postDelayed({
                        listener.resetOverlay()
                        listener.onClick(pos)
                    }, 500L)
                }
            }
        }
    }

    inner class LargeCardViewholder(private val binding: CardItemLargeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardState) {
            binding.ivFace.setImageResource(card.image)
            if (card.state == State.FLIPPED || card.isMatched) {
                binding.ivFace.visibility = View.VISIBLE
                binding.ivRear.visibility = View.GONE
            } else {
                binding.ivFace.visibility = View.GONE
                binding.ivRear.visibility = View.VISIBLE
            }
            var pos = -1
            if (adapterPosition != RecyclerView.NO_POSITION) {
                pos = adapterPosition
            }
            if (pos != -1) {
                binding.cardLayout.setOnClickListener {
                    binding.ivRear.visibility = View.GONE
                    binding.ivFace.visibility = View.VISIBLE
                    listener.resetOverlay()
                    android.os.Handler().postDelayed({
                        listener.resetOverlay()
                        listener.onClick(pos)
                    }, 500L)
                }
            }
        }
    }

    inner class MediumCardViewholder(private val binding: CardItemMediumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardState) {
            binding.ivFace.setImageResource(card.image)
            if (card.state == State.FLIPPED || card.isMatched) {
                binding.ivFace.visibility = View.VISIBLE
                binding.ivRear.visibility = View.GONE
            } else {
                binding.ivFace.visibility = View.GONE
                binding.ivRear.visibility = View.VISIBLE
            }
            var pos = -1
            if (adapterPosition != RecyclerView.NO_POSITION) {
                pos = adapterPosition
            }
            if (pos != -1) {
                binding.cardLayout.setOnClickListener {
                    binding.ivRear.visibility = View.GONE
                    binding.ivFace.visibility = View.VISIBLE
                    listener.resetOverlay()
                    android.os.Handler().postDelayed({
                        listener.resetOverlay()
                        listener.onClick(pos)
                    }, 500L)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(cardList: List<CardState>) {
        this.cardsList = cardList.toMutableList()
        notifyDataSetChanged()
    }
}