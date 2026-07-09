package com.tsvigun.workmatestarwars.domain

data class StarWarsCharacter(
    val id: String,
    val name: String,
    val birthYear: String,
    val height: String,
    val mass: String,
    val films: List<Film> = emptyList()
)