package com.example.matchmatch.adapter

import android.animation.Animator
import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
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
            binding.ivFace.setImageResource(card.image)
            if (card.state == State.FLIPPED || card.isMatched) {
                binding.ivFace.visibility = View.VISIBLE
                binding.ivRear.visibility = View.GONE
            } else {
                binding.ivFace.visibility = View.GONE
                binding.ivRear.visibility = View.VISIBLE
            }
                val context = binding.root.context
                val pos = adapterPosition
                binding.cardLayout.setOnClickListener {
                    listener.onClick(pos)
                    /*AnimatorInflater.loadAnimator(context, R.animator.card_flip_90).apply {
                        setTarget(binding.ivRear)
                        addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(p0: Animator) {
                                listener.resetOverlay()
                            }

                            override fun onAnimationEnd(p0: Animator) {
                                binding.ivRear.visibility = View.GONE
                                binding.ivFace.visibility = View.VISIBLE
                            }

                            override fun onAnimationCancel(p0: Animator) {
                            }

                            override fun onAnimationRepeat(p0: Animator) {
                            }

                        })
                        start()
                    }*/
                    /*AnimatorInflater.loadAnimator(context, R.animator.card_flip_180).apply {
                        setTarget(binding.ivFace)
                        addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(p0: Animator) {
                            }

                            override fun onAnimationEnd(p0: Animator) {
                                //listener.resetOverlay()
                                listener.onClick(pos)
                            }

                            override fun onAnimationCancel(p0: Animator) {
                            }

                            override fun onAnimationRepeat(p0: Animator) {
                            }
                        })
                        start()
                    }*/
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