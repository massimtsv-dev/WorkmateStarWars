package com.tsvigun.workmatestarwars.presentation.main

import com.tsvigun.workmatestarwars.domain.StarWarsCharacter

sealed interface MainState {
    object Loading : MainState
    data class Success(val characters: List<StarWarsCharacter>) : MainState
    data class Error(val message: String) : MainState
}