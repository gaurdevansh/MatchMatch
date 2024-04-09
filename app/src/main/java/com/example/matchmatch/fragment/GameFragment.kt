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
import com.example.matchmatch.utils.GameItemDecoration
import com.example.matchmatch.utils.GameLevel
import com.google.gson.Gson

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var level: GameLevel
    private lateinit var gameAdapter: GameAdapter
    private var cardList: MutableList<Int> = mutableListOf()
    private lateinit var gameRecyclerView: RecyclerView

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
        gameAdapter = GameAdapter()
        gameRecyclerView.layoutManager = GridLayoutManager(context, 2)
        gameRecyclerView.addItemDecoration(GameItemDecoration(40))
        gameRecyclerView.adapter = gameAdapter
        setupCardList()
    }

    private fun setupCardList() {
        cardList.clear()
        cardList.add(R.drawable.eagle)
        cardList.add(R.drawable.squirrel)
        cardList.add(R.drawable.tiger)
        cardList.add(R.drawable.squirrel)
        cardList.add(R.drawable.tiger)
        cardList.add(R.drawable.eagle)
        gameAdapter.updateList(cardList)
    }
}