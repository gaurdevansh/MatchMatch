package com.example.matchmatch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.matchmatch.R
import com.example.matchmatch.databinding.FragmentLevelSelectionBinding
import com.example.matchmatch.utils.GameLevel
import com.google.gson.Gson

class LevelSelectionFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLevelSelectionBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        binding.btnBeginner.setOnClickListener(this)
        binding.btnIntermediate.setOnClickListener(this)
        binding.btnAdvanced.setOnClickListener(this)
        binding.btnExpert.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBeginner -> {
                val bundle = Bundle().apply {
                    putString("level", GameLevel.BEGINNER.name)
                }
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment, bundle)
            }
            binding.btnIntermediate -> {
                val bundle = Bundle().apply {
                    putSerializable("level", GameLevel.INTERMEDIATE)
                }
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment, bundle)
            }
            binding.btnAdvanced -> {
                val bundle = Bundle().apply {
                    putSerializable("level", GameLevel.ADVANCED)
                }
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment, bundle)
            }
            binding.btnExpert -> {
                val bundle = Bundle().apply {
                    putSerializable("level", GameLevel.EXPERT)
                }
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment, bundle)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("level", GameLevel.BEGINNER.name)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

}