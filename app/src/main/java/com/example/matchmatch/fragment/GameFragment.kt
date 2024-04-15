package com.example.matchmatch.fragment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmatch.MainActivity
import com.example.matchmatch.R
import com.example.matchmatch.adapter.GameAdapter
import com.example.matchmatch.databinding.FragmentGameBinding
import com.example.matchmatch.model.CardState
import com.example.matchmatch.utils.*
import kotlinx.coroutines.*

class GameFragment : Fragment(), OnCardClickListener {

    private lateinit var binding: FragmentGameBinding
    private lateinit var gameLevel: GameLevel
    private lateinit var gameAdapter: GameAdapter
    private var cardList: MutableList<CardState> = mutableListOf()
    private lateinit var gameRecyclerView: RecyclerView
    private var currentFlipCard = 0
    private var previousFlipIndex = -1
    private var overlayEnabled = false
    private var currentMatches = 0
    private var totalFlips = 0
    private lateinit var navController: NavController
    private var imageList = listOf(
        CardState(1, R.drawable.eagle), CardState(1, R.drawable.eagle),
        CardState(2, R.drawable.squirrel), CardState(2, R.drawable.squirrel),
        CardState(3, R.drawable.tiger),  CardState(3, R.drawable.tiger),
        CardState(4, R.drawable.elephant), CardState(4, R.drawable.elephant),
        CardState(5, R.drawable.crocodile), CardState(5, R.drawable.crocodile),
        CardState(6, R.drawable.shark), CardState(6, R.drawable.shark)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameLevel = (context as MainActivity).getGameLevel()
        setupGame()
        navController = Navigation.findNavController(requireView())
    }

    private fun setupGame() {
        binding.matches.text = "0"
        binding.attempts.text = "0"
        setupGameRecyclerView()
    }

    private fun setupGameRecyclerView() {
        gameRecyclerView = binding.rvGame
        gameAdapter = GameAdapter(this, gameLevel)
        setupLayoutManager()
        gameRecyclerView.addItemDecoration(GameItemDecoration(40))
        gameRecyclerView.adapter = gameAdapter
        setupCardList(gameLevel)
    }

    private fun setupLayoutManager() {
        gameRecyclerView.layoutManager = when (gameLevel) {
            GameLevel.BEGINNER -> {
                GridLayoutManager(context, 2)
            }
            GameLevel.INTERMEDIATE -> {
                GridLayoutManager(context, 2)
            }
            GameLevel.ADVANCED -> {
                GridLayoutManager(context, 2)
            }
            else -> {
                GridLayoutManager(context, 3)
            }
        }
    }

    private fun setupCardList(level: GameLevel) {
        cardList.clear()
        cardList = when (level) {
            GameLevel.BEGINNER -> {
                imageList.take(6).toMutableList()
            }
            GameLevel.INTERMEDIATE -> {
                imageList.take(8).toMutableList()
            }
            GameLevel.ADVANCED -> {
                imageList.take(10).toMutableList()
            }
            else -> {
                imageList.toMutableList()
            }
        }
        cardList.shuffle()
        gameAdapter.updateList(cardList)
    }

    @Suppress("DEPRECATION")
    override fun onClick(index: Int) {
        if (cardList[index].isMatched || index == previousFlipIndex)
            return
        updateCardList(index)
    }

    private fun updateCardList(index: Int) {
        if (currentFlipCard == 0) {
            cardList[index].state = State.FLIPPED
            currentFlipCard = 1
            previousFlipIndex = index
        } else if (currentFlipCard == 1) {
            if (cardList[index].id == cardList[previousFlipIndex].id) {
                cardList[index].isMatched = true
                cardList[previousFlipIndex].isMatched = true
                cardList[index].state = State.FLIPPED
                cardList[index].isEnabled = false
                cardList[previousFlipIndex].isEnabled = false
                currentMatches += 1
            } else {
                cardList[previousFlipIndex].state = State.HIDDEN
                cardList[index].state = State.HIDDEN
            }
            previousFlipIndex = -1
            currentFlipCard = 0
            totalFlips += 1
        }
        gameAdapter.updateList(cardList)
        checkIfAllMatched()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun resetOverlay() {
        if (overlayEnabled) {
            binding.overlay.setOnTouchListener { _, _ ->
                false
            }
        } else {
            binding.overlay.setOnTouchListener { _, _ ->
                true
            }
        }
        overlayEnabled = !overlayEnabled
    }

    private fun checkIfAllMatched() {
        if (currentMatches == gameLevel.totalMatches) {
            Toast.makeText(requireContext(), "Congrats! All Cards Matched", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable {
                navController.navigate(R.id.action_gameFragment_to_levelSelectionFragment)
                cleanup()
            }, 2000L)
        }
        binding.matches.text = currentMatches.toString()
        binding.attempts.text = totalFlips.toString()
    }

    private fun cleanup() {
        currentMatches = 0
        totalFlips = 0
        currentMatches = 0
        cardList.clear()
    }
}

