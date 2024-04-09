package com.example.matchmatch.model

import com.example.matchmatch.utils.State

data class CardState(
    val id: Int,
    val image: Int,
    var state: State = State.HIDDEN,
    var isMatched: Boolean = false
)
