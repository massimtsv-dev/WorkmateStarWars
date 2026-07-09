package com.tsvigun.workmatestarwars.data.network

import retrofit2.http.GET
import retrofit2.http.Url

interface SwapiApi {
    @GET("people/")
    suspend fun getAllCharacters(): CharacterResponse

    @GET
    suspend fun getFilmByUrl(@Url url: String): FilmDto
}

data class FilmDto(
    val title: String,
    val opening_crawl: String
)