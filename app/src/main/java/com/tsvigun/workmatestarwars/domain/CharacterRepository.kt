package com.tsvigun.workmatestarwars.domain

interface CharacterRepository {
    suspend fun getAllCharacters(): List<StarWarsCharacter>
    suspend fun getCharacterById(id: String): StarWarsCharacter
}
