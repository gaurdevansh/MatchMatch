package com.example.matchmatch.utils

enum class GameLevel(val totalMatches: Int) {
    BEGINNER(3),
    INTERMEDIATE(4),
    ADVANCED(5),
    EXPERT(6)
}

enum class State {
    HIDDEN,
    FLIPPED
}