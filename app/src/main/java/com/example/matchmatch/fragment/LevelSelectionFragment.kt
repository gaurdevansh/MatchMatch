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
import com.example.matchmatch.utils.DataListener
import com.example.matchmatch.utils.GameLevel
import com.google.gson.Gson

class LevelSelectionFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLevelSelectionBinding
    private lateinit var navController: NavController
    private lateinit var dataListener: DataListener

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
        if (context is DataListener) {
            dataListener = context as DataListener
        }
        binding.btnBeginner.setOnClickListener(this)
        binding.btnIntermediate.setOnClickListener(this)
        binding.btnAdvanced.setOnClickListener(this)
        binding.btnExpert.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBeginner -> {
                dataListener.onDataReceived(GameLevel.BEGINNER)
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment)
            }
            binding.btnIntermediate -> {
                dataListener.onDataReceived(GameLevel.INTERMEDIATE)
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment)
            }
            binding.btnAdvanced -> {
                dataListener.onDataReceived(GameLevel.ADVANCED)
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment)
            }
            binding.btnExpert -> {
                dataListener.onDataReceived(GameLevel.EXPERT)
                navController.navigate(R.id.action_levelSelectionFragment_to_gameFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

}