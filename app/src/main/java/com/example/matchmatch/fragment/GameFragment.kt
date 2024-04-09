package com.example.matchmatch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmatch.R
import com.example.matchmatch.adapter.GameAdapter
import com.example.matchmatch.databinding.FragmentGameBinding
import com.example.matchmatch.model.CardState
import com.example.matchmatch.utils.GameItemDecoration
import com.example.matchmatch.utils.GameLevel
import com.example.matchmatch.utils.OnCardClickListener
import com.example.matchmatch.utils.State
import com.google.gson.Gson

class GameFragment : Fragment(), OnCardClickListener {

    private lateinit var binding: FragmentGameBinding
    private lateinit var level: GameLevel
    private lateinit var gameAdapter: GameAdapter
    private var cardList: MutableList<CardState> = mutableListOf()
    private lateinit var gameRecyclerView: RecyclerView
    private var currentFlipCard = 0
    private var previousFlipIndex = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val levelName = savedInstanceState?.getString("level")
        level = levelName?.let { GameLevel.valueOf(it) } ?: GameLevel.BEGINNER
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGame()
    }

    private fun setupGame() {
        binding.tvNoOfFlips.text = "0"
        binding.tvNoOfCardsMatched.text = "0"
        setupGameRecyclerView()
    }

    private fun setupGameRecyclerView() {
        gameRecyclerView = binding.rvGame
        gameAdapter = GameAdapter(this)
        gameRecyclerView.layoutManager = GridLayoutManager(context, 2)
        gameRecyclerView.addItemDecoration(GameItemDecoration(40))
        gameRecyclerView.adapter = gameAdapter
        setupCardList()
    }

    private fun setupCardList() {
        cardList.clear()
        cardList.add(CardState(1, R.drawable.eagle))
        cardList.add(CardState(2, R.drawable.squirrel))
        cardList.add(CardState(3, R.drawable.tiger))
        cardList.add(CardState(2, R.drawable.squirrel))
        cardList.add(CardState(3, R.drawable.tiger))
        cardList.add(CardState(1, R.drawable.eagle))
        gameAdapter.updateList(cardList)
    }

    override fun onClick(index: Int) {
        if (currentFlipCard == 0) {
            cardList[index].state = State.FLIPPED
            currentFlipCard = 1
            previousFlipIndex = index
            gameAdapter.updateList(cardList)
        } else if (currentFlipCard == 1) {
            if (cardList[index].id == cardList[previousFlipIndex].id) {
                cardList[index].isMatched = true
                cardList[previousFlipIndex].isMatched = true
                cardList[index].state = State.FLIPPED
            } else {
                cardList[previousFlipIndex].state = State.HIDDEN
            }
            currentFlipCard = 0
            gameAdapter.updateList(cardList)
        }
    }

    private fun getMatchingCardState(id: Int, index: Int): Int {
        cardList.forEachIndexed { i, c ->
            if (i != index && c.id == id) {
                return i
            }
        }
        return -1
    }
}