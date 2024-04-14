package com.example.matchmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.matchmatch.fragment.GameFragment
import com.example.matchmatch.utils.DataListener
import com.example.matchmatch.utils.GameLevel

class MainActivity : AppCompatActivity(), DataListener {

    private lateinit var navController: NavController
    private var gameLevel: GameLevel = GameLevel.BEGINNER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onDataReceived(level: GameLevel) {
        this.gameLevel = level
    }

    fun getGameLevel(): GameLevel {
        return gameLevel
    }

}