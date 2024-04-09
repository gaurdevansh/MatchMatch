package com.example.matchmatch.model

import com.example.matchmatch.utils.State

data class CardState(
    val image: Int,
    val state: State = State.HIDDEN,
    val isMatched: Boolean = false
)
