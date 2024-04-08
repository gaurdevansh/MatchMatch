package com.example.matchmatch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matchmatch.databinding.FragmentGameBinding
import com.example.matchmatch.utils.GameLevel
import com.google.gson.Gson

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var level: GameLevel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json = savedInstanceState?.getString("level")
        val gson = Gson()
        level = gson.fromJson(json, GameLevel::class.java) ?: GameLevel.BEGINNER

        when (level) {
            GameLevel.BEGINNER -> {
                binding.tv.text = GameLevel.BEGINNER.toString()
            }
            GameLevel.INTERMEDIATE -> {
                binding.tv.text = GameLevel.INTERMEDIATE.toString()
            }
            GameLevel.ADVANCED -> {
                binding.tv.text = GameLevel.ADVANCED.toString()
            }
            GameLevel.EXPERT -> {
                binding.tv.text = GameLevel.EXPERT.toString()
            }
        }
    }
}